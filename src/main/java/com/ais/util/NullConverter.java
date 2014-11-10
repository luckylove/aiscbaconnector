package com.ais.util;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.core.ReferencingMarshallingContext;
import com.thoughtworks.xstream.core.util.ArrayIterator;
import com.thoughtworks.xstream.core.util.FastField;
import com.thoughtworks.xstream.core.util.HierarchicalStreams;
import com.thoughtworks.xstream.core.util.Primitives;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.Mapper;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * User: son.nguyen
 * Date: 11/15/13
 * Time: 12:06 PM
 */
public class NullConverter extends ReflectionConverter {

    public NullConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
        super(mapper, reflectionProvider);
    }

    @Override
    protected void doMarshal(final Object source, final HierarchicalStreamWriter writer,
                             final MarshallingContext context) {
        final List fields = new ArrayList();
        final Map defaultFieldDefinition = new HashMap();

        // Attributes might be preferred to child elements ...
        reflectionProvider.visitSerializableFields(source, new ReflectionProvider.Visitor() {
            final Set writtenAttributes = new HashSet();

            public void visit(String fieldName, Class type, Class definedIn, Object value) {
                if (!mapper.shouldSerializeMember(definedIn, fieldName)) {
                    return;
                }
                if (!defaultFieldDefinition.containsKey(fieldName)) {
                    Class lookupType = source.getClass();
                    // See XSTR-457 and OmitFieldsTest
                    if (definedIn != source.getClass()
                            && !mapper.shouldSerializeMember(lookupType, fieldName)) {
                        lookupType = definedIn;
                    }
                    defaultFieldDefinition.put(
                            fieldName, reflectionProvider.getField(lookupType, fieldName));
                }

                SingleValueConverter converter = mapper.getConverterFromItemType(
                        fieldName, type, definedIn);
                if (converter != null) {
                    final String attribute = mapper.aliasForAttribute(mapper.serializedMember(
                            definedIn, fieldName));
                    if (value != null) {
                        if (writtenAttributes.contains(fieldName)) { // TODO: use attribute
                            throw new ConversionException("Cannot write field with name '"
                                    + fieldName
                                    + "' twice as attribute for object of type "
                                    + source.getClass().getName());
                        }
                        final String str = converter.toString(value);
                        if (str != null) {
                            writer.addAttribute(attribute, str);
                        }
                    }
                    writtenAttributes.add(fieldName); // TODO: use attribute
                } else {
                    fields.add(new FieldInfo(fieldName, type, definedIn, value));
                }
            }
        });

        new Object() {
            {
                for (Iterator fieldIter = fields.iterator(); fieldIter.hasNext();) {
                    FieldInfo info = (FieldInfo)fieldIter.next();
                    if (info.value != null) {
                        Mapper.ImplicitCollectionMapping mapping = mapper
                                .getImplicitCollectionDefForFieldName(
                                        source.getClass(), info.fieldName);
                        if (mapping != null) {
                            if (context instanceof ReferencingMarshallingContext) {
                                if (info.value != Collections.EMPTY_LIST
                                        && info.value != Collections.EMPTY_SET
                                        && info.value != Collections.EMPTY_MAP) {
                                    ReferencingMarshallingContext refContext = (ReferencingMarshallingContext)context;
                                    refContext.registerImplicit(info.value);
                                }
                            }
                            final boolean isCollection = info.value instanceof Collection;
                            final boolean isMap = info.value instanceof Map;
                            final boolean isEntry = isMap && mapping.getKeyFieldName() == null;
                            final boolean isArray = info.value.getClass().isArray();
                            for (Iterator iter = isArray
                                    ? new ArrayIterator(info.value)
                                    : isCollection ? ((Collection)info.value).iterator() : isEntry
                                    ? ((Map)info.value).entrySet().iterator()
                                    : ((Map)info.value).values().iterator(); iter.hasNext();) {
                                Object obj = iter.next();
                                final String itemName;
                                final Class itemType;
                                if (obj == null) {
                                    itemType = Object.class;
                                    itemName = mapper.serializedClass(null);
                                } else if (isEntry) {
                                    final String entryName = mapping.getItemFieldName() != null
                                            ? mapping.getItemFieldName()
                                            : mapper.serializedClass(Map.Entry.class);
                                    Map.Entry entry = (Map.Entry)obj;
                                    ExtendedHierarchicalStreamWriterHelper.startNode(
                                            writer, entryName, entry.getClass());
                                    writeItem(entry.getKey(), context, writer);
                                    writeItem(entry.getValue(), context, writer);
                                    writer.endNode();
                                    continue;
                                } else if (mapping.getItemFieldName() != null) {
                                    itemType = mapping.getItemType();
                                    itemName = mapping.getItemFieldName();
                                } else {
                                    itemType = obj.getClass();
                                    itemName = mapper.serializedClass(itemType);
                                }
                                writeField(
                                        info.fieldName, itemName, itemType, info.definedIn, obj);
                            }
                        } else {
                            writeField(
                                    info.fieldName, null, info.type, info.definedIn, info.value);
                        }
                    }
                }

            }

            void writeField(String fieldName, String aliasName, Class fieldType,
                            Class definedIn, Object newObj) {
                Class actualType = newObj != null ? newObj.getClass() : fieldType;
                ExtendedHierarchicalStreamWriterHelper.startNode(writer, aliasName != null
                        ? aliasName
                        : mapper.serializedMember(source.getClass(), fieldName), actualType);

               /* if (newObj ==  null) {
                    newObj = new String("");
                }*/

                if (newObj != null) {
                    Class defaultType = mapper.defaultImplementationOf(fieldType);
                    if (!actualType.equals(defaultType)) {
                        String serializedClassName = mapper.serializedClass(actualType);
                        if (!serializedClassName.equals(mapper.serializedClass(defaultType))) {
                            String attributeName = mapper.aliasForSystemAttribute("class");
                            if (attributeName != null) {
                                if("java.util.Date".equals(defaultType.getName()) && newObj instanceof String && StringUtils.isEmpty((String)newObj)){
                                    //ignore if type date is null
                                } else {
                                    if("sql-timestamp".equals(serializedClassName)){            //some spec case (sub class)
                                        writer.addAttribute(attributeName, serializedClassName);
                                    } else {
                                        writer.addAttribute(attributeName, mapper.serializedClass(defaultType));
                                    }
                                }
                            }
                        }
                    }

                    final Field defaultField = (Field)defaultFieldDefinition.get(fieldName);
                    if (defaultField.getDeclaringClass() != definedIn) {
                        String attributeName = mapper.aliasForSystemAttribute("defined-in");
                        if (attributeName != null) {
                            writer.addAttribute(
                                    attributeName, mapper.serializedClass(definedIn));
                        }
                    }

                    Field field = reflectionProvider.getField(definedIn, fieldName);
                    marshallField(context, newObj, field);
                }
                writer.endNode();
            }

            void writeItem(Object item, MarshallingContext context,
                           HierarchicalStreamWriter writer) {
                if (item == null) {
                    String name = mapper.serializedClass(null);
                    ExtendedHierarchicalStreamWriterHelper.startNode(
                            writer, name, Mapper.Null.class);
                    writer.endNode();
                } else {
                    String name = mapper.serializedClass(item.getClass());
                    ExtendedHierarchicalStreamWriterHelper.startNode(
                            writer, name, item.getClass());
                    context.convertAnother(item);
                    writer.endNode();
                }
            }
        };
    }


    private static class FieldInfo {
        final String fieldName;
        final Class type;
        final Class definedIn;
        Object value = new Object();

        FieldInfo(String fieldName, Class type, Class definedIn, Object value) {
            this.fieldName = fieldName;
            this.type = type;
            this.definedIn = definedIn;
            if (value == null && !"orResult".equals(fieldName)) {
                this.value = new String("");
            } else {
                this.value = value;
            }
        }

    }



}
