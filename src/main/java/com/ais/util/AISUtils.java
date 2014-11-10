package com.ais.util;

import com.googlecode.jcsv.writer.CSVEntryConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: son.nguyen
 * Date: 10/28/13
 * Time: 4:49 PM
 */
public class AISUtils {

    private static Logger logger = LoggerFactory.getLogger(AISUtils.class);

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat dfShort = new SimpleDateFormat("yyyyMMdd");

    public static DataSource initDataSource(com.ais.config.model.DataSource dataSource) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(dataSource.getDriver());
        basicDataSource.setUrl(dataSource.getUrl());
        basicDataSource.setUsername(dataSource.getUsername());
        basicDataSource.setPassword(dataSource.getPassword());
        if ("oracle.jdbc.driver.OracleDriver".equals(dataSource.getDriver())) {
            basicDataSource.setConnectionProperties(dataSource.getConnectionProperties());
            basicDataSource.setRemoveAbandoned(dataSource.isRemoveAbandoned());
            basicDataSource.setRemoveAbandonedTimeout(dataSource.getRemoveAbandonedTimeout());
            basicDataSource.setMaxActive(dataSource.getMaxActive());
            basicDataSource.setMaxIdle(dataSource.getMaxIdle());
            basicDataSource.setMaxWait(dataSource.getMaxWait());
            basicDataSource.setTestWhileIdle(dataSource.isTestWhileIdle());
            basicDataSource.setTimeBetweenEvictionRunsMillis(dataSource.getTimeBetweenEvictionRunsMillis());
            basicDataSource.setMinEvictableIdleTimeMillis(dataSource.getMinEvictableIdleTimeMillis());
            basicDataSource.setTestOnBorrow(dataSource.isTestOnBorrow());
            basicDataSource.setValidationQuery(dataSource.getValidationQuery());
        } else if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(dataSource.getDriver())){
            basicDataSource.setRemoveAbandoned(dataSource.isRemoveAbandoned());
            basicDataSource.setRemoveAbandonedTimeout(dataSource.getRemoveAbandonedTimeout());
            basicDataSource.setMaxActive(dataSource.getMaxActive());
            basicDataSource.setMaxIdle(dataSource.getMaxIdle());
            basicDataSource.setMaxWait(dataSource.getMaxWait());
        }
        return basicDataSource;
    }

    public static JdbcTemplate initJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    public static String currDate2Str() {
        Date date = Calendar.getInstance().getTime();
        return df.format(date);
    }

    public static String currShortDate2Str() {
        Date date = Calendar.getInstance().getTime();
        return dfShort.format(date);
    }

    public static <T> List<T> readAllDataFromDB(Class<T> clazz, String tableName, JdbcTemplate jdbcTemplate) throws IllegalAccessException, InstantiationException {
        logger.info("Load xml from database: " + tableName);
        List<T> rs = new ArrayList<T>();
        if (jdbcTemplate != null) {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from " + tableName);
            for (Map<String, Object> m : maps) {
                T t = clazz.newInstance();
                AISUtils.map2Object(t, m);
                rs.add(t);
            }
        }
        return rs;
    }

    public static <T> List<T> readAllDataFromDB(Class<T> clazz, String tableName, String joinTable, JdbcTemplate jdbcTemplate) throws IllegalAccessException, InstantiationException {
        logger.info("Load xml from database: " + tableName);
        List<T> rs = new ArrayList<T>();
        if (jdbcTemplate != null) {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from " + tableName + joinTable);
            for (Map<String, Object> m : maps) {
                T t = clazz.newInstance();
                AISUtils.map2Object(t, m);
                rs.add(t);
            }
        }
        return rs;
    }

    public static <T> List<T> readAllDataFromFile(File file, String tableName, Class<T> clazz) {
        logger.info("Load xml from file: " + tableName + ".xml");
        List<T> rs = new ArrayList<T>();
        XStream xstream = new XStream(new StaxDriver());
        xstream.registerConverter(new AISDateConverter(), XStream.PRIORITY_NORMAL);
        xstream.registerConverter(new AISLongConverter(), XStream.PRIORITY_NORMAL);
        xstream.registerConverter(new AISDoubleConverter(), XStream.PRIORITY_NORMAL);
        xstream.alias("resources", List.class);
        xstream.alias("resource", clazz);
        InputStreamReader iread = null;
        try {
            iread = new InputStreamReader(new FileInputStream(file), "UTF-8");
            rs = (List<T>) xstream.fromXML(iread);
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        } catch (FileNotFoundException e) {
            logger.error("", e);
        } finally {
            if (iread != null) {
                try {
                    iread.close();
                } catch (IOException e) {
                }
            }
        }
        return rs;
    }

    public static <T> void writeAllData(File file, String tableName, Class<T> clazz, List<T> list) throws IOException {
        XStream xstream = new XStream(new StaxDriver());

        NullConverter reflectionConverter = new
                NullConverter(xstream.getMapper(),
                xstream.getReflectionProvider());
        xstream.registerConverter(reflectionConverter, XStream.PRIORITY_VERY_LOW);

        xstream.alias("resources", List.class);
        xstream.alias("resource", clazz);
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        xstream.toXML(list, writer);
        try {
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error("", e);
        }
        logger.info("Store to local file : " + tableName + ".xml");
    }

    public static <T> List<T> xml2Ob(String xml, Class<T> clazz) {
        List<T> rs = null;
        XStream xstream = new XStream(new StaxDriver());
        xstream.registerConverter(new AISDateConverter(), XStream.PRIORITY_NORMAL);
        xstream.registerConverter(new AISLongConverter(), XStream.PRIORITY_NORMAL);
        xstream.registerConverter(new AISDoubleConverter(), XStream.PRIORITY_NORMAL);
        xstream.alias("resources", List.class);
        xstream.alias("resource", clazz);
        rs = (List<T>) xstream.fromXML(xml);
        return rs;
    }

    public static <T> void map2Object(T config, Map<String, Object> map) {
        Field[] fields = config.getClass().getDeclaredFields();
        Map<String, Field> mapFields = new HashMap<String, Field>();
        for (Field field : fields) {
            mapFields.put(field.getName(), field);
        }
        //get one level supper class
        if (config.getClass().getSuperclass() != null) {
            Field[] supperFields = config.getClass().getSuperclass().getDeclaredFields();
            for (Field field : supperFields) {
                mapFields.put(field.getName(), field);
            }
        }
        Set set = map.keySet();
        Iterator it = set.iterator();
        Method method;
        String key, key1, key2;
        Type type;

        while (it.hasNext()) {
            key = (String) it.next();
            key1 = toCamelCase(key);
            key2 = lowserCaseFirst(key1);
            Object obv = map.get(key);
            Field configField = mapFields.get(key2);
            if (configField != null) {
                try {
                    method = config.getClass().getMethod(
                            "set" + key1, new Class[]{configField.getType()});
                    type = configField.getGenericType();

                    if (type.equals(Integer.class)) {
                        method.invoke(config, AISUtils.ob2I(obv));
                    } else if (type.equals(Long.class)) {
                        method.invoke(config, AISUtils.ob2L(obv));
                    } else if (type.equals(Date.class)) {
                        method.invoke(config, AISUtils.ob2D(obv));
                    } else {
                        method.invoke(config, AISUtils.ob2S(obv));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> SqlParameterSource ob2SqlSource(T config) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        Field[] fields = config.getClass().getDeclaredFields();
        Method method;
        for (Field f : fields) {
            String name = f.getName();
            String reName = "IN_" + toRevertCamelCase(name);
            if (StringUtils.isNotEmpty(reName)) {
                try {
                    method = config.getClass().getMethod(
                            "get" + upperCaseFirst(name));

                    Object invoke = method.invoke(config);
                    in.addValue(reName, invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return in;
    }

    public static <T> SqlParameterSource ob2SqlSource(T config, String prefix) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        Field[] fields = config.getClass().getDeclaredFields();
        Method method;
        for (Field f : fields) {
            String name = f.getName();
            String reName = toRevertCamelCase(name);
            if (StringUtils.isNotEmpty(prefix)) {
                reName = prefix + "_" + reName;
            }
            if (StringUtils.isNotEmpty(reName)) {
                try {
                    method = config.getClass().getMethod(
                            "get" + upperCaseFirst(name));

                    Object invoke = method.invoke(config);
                    in.addValue(reName, invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return in;
    }

    public static <T> SqlParameterSource ob2SqlSource1(T config, String prefix) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        Field[] fields = config.getClass().getDeclaredFields();
        Method method;
        for (Field f : fields) {
            String name = f.getName();
            String reName = toRevertCamelCase(name);
            if (StringUtils.isNotEmpty(prefix)) {
                reName = prefix + "_" + reName;
            }
            if (StringUtils.isNotEmpty(reName)) {
                try {
                    method = config.getClass().getMethod(
                            "get" + upperCaseFirst1(name));

                    Object invoke = method.invoke(config);
                    in.addValue(reName, invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return in;
    }
    public static <T> CSVEntryConverter<T> ob2Strr(T config) {
        Field[] fields = config.getClass().getDeclaredFields();
        String[] res = new String[fields.length];
        Method method;
        int i = 0;
        for (Field f : fields) {
            try {
                method = config.getClass().getMethod(
                        "get" + upperCaseFirst(f.getName()));

                Object invoke = method.invoke(config);
                if (invoke != null) {
                    res[i++] = String.valueOf(invoke);
                } else {
                    res[i++] = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        final String[] result = res;
        return new CSVEntryConverter<T>() {
            @Override
            public String[] convertEntry(T t) {
                return result;
            }
        };
    }

    public static <T> CSVEntryConverter<T> ob2Strr1(T config) {
        Field[] fields = config.getClass().getDeclaredFields();
        String[] res = new String[fields.length];
        Method method;
        int i = 0;
        for (Field f : fields) {
            try {
                method = config.getClass().getMethod(
                        "get" + upperCaseFirst1(f.getName()));

                Object invoke = method.invoke(config);
                if (invoke != null) {
                    res[i++] = String.valueOf(invoke);
                } else {
                    res[i++] = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        final String[] result = res;
        return new CSVEntryConverter<T>() {
            @Override
            public String[] convertEntry(T t) {
                return result;
            }
        };
    }

    private static String toRevertCamelCase(String str) {
        String camelCaseString = "";
        if (StringUtils.isNotEmpty(str)) {
            String[] r = str.split("(?=\\p{Upper})");
            return camelCaseString = StringUtils.join(r, "_").toUpperCase();
        }
        return null;
    }

    private static String lowserCaseFirst(String str) {
        if (StringUtils.isNotEmpty(str)) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return "";
    }

    private static String upperCaseFirst(String str) {
        if (StringUtils.isNotEmpty(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return "";
    }

    private static String upperCaseFirst1(String str) {
        if (StringUtils.isNotEmpty(str)) {
            if (!Character.isUpperCase(str.charAt(1))) {
                return str.substring(0, 1).toUpperCase() + str.substring(1);
            } else {
                return str;
            }
        }
        return "";
    }

    private static String toCamelCase(String str) {
        String camelCaseString = "";
        if (StringUtils.isNotEmpty(str)) {
            str = str.replaceFirst("^OUT_", "");
            str = str.replaceFirst("^O_", "");
            String[] parts = str.split("_");
            for (String part : parts) {
                camelCaseString = camelCaseString + toProperCase(part);
            }
        }
        return camelCaseString;
    }

    public static String toVariable(String str) {
        String camelCaseString = "";
        if (StringUtils.isNotEmpty(str)) {
            str = str.replaceFirst("^OUT_", "");
            str = str.replaceFirst("^O_", "");
            String[] parts = str.split("_");
            int i = 0;
            for (String part : parts) {
                if (i == 0) {
                    camelCaseString += part.toLowerCase();
                } else {
                    camelCaseString += toProperCase(part);
                }
                i++;
            }
        }
        return camelCaseString;
    }

    private static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }

    public static Long ob2L(Object v) {
        if (v != null) {
            if (v instanceof BigDecimal) {
                return ((BigDecimal) v).longValue();
            } else {
                return Long.valueOf(String.valueOf(v));
            }
        }
        return null;
    }

    public static Integer ob2I(Object v) {
        if (v != null) {
            if (v instanceof BigDecimal) {
                return ((BigDecimal) v).intValue();
            } else {
                return Integer.valueOf(String.valueOf(v));
            }
        }
        return null;
    }

    public static String ob2S(Object v) {
        if (v != null) {
            return v.toString().trim();
        }
        return null;
    }

    public static Date ob2D(Object v) {
        if (v != null) {
            return (Date) v;
        }
        return null;
    }

    public static String null2Str(String v) {
        if (v != null) {
            return  v;
        }
        return "";
    }

    public static void close(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (Exception e) {

            }
        }
    }

    public static String getConfigFile(String rootPath, String fileName, int level) throws Exception {
        if (level < 2) {
            String path = rootPath;
            if (path == null) {
                path = AISUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            }
            logger.debug(">>>>>>>>>>>>>>> current path : " + level + " : " + path);
            path = new File(URLDecoder.decode(path, "UTF-8")).getAbsolutePath();
            if (StringUtils.isNotEmpty(path)) {
                int i = path.lastIndexOf(File.separator);
                if (i > 0) {
                    path = path.substring(0, i);
                    String npath = path + File.separator + fileName;
                    File f = new File(npath);
                    if (f.exists()) {
                        return f.getAbsolutePath();
                    } else {
                        //try 1 uplevel
                        return AISUtils.getConfigFile(path, fileName, ++level);
                    }
                }
            }
        }
        return null;
    }

    public static String upperCase(String val) {
        if (val != null) {
            return val.toUpperCase();
        }
        return val;
    }


    public static long str2sec(String source) {
        long sec = 0;
        if (StringUtils.isNotEmpty(source)) {
            String[] split = source.split(":");
            SimpleDateFormat sf = null;
            if (split.length == 2) {
                sf = new SimpleDateFormat("mm:ss");
            }else if (split.length == 3) {
                sf = new SimpleDateFormat("hh:mm:ss");
            }
            if (sf == null) {
                logger.error("wrong format " + source);
                return 0;
            }

            try {
                Date parse = sf.parse(source);
                sec = parse.getHours() * 3600 + parse.getMinutes() *60 + parse.getSeconds();
                System.out.println(sec);
            } catch (ParseException e) {
                logger.error("error", e);
            }
        }
        return sec;
    }

    public static long datediff(String type, String startDate, String endDate) {
        long sec = 0;
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            try {
                Date start = sf.parse(startDate);
                Date end = sf.parse(endDate);
                long diff = end.getTime() - start.getTime();
                //convert diff to another format;
                if ("S".equals(type)) {
                  sec = diff/1000;
                } else if ("M".equals(type)) {
                    sec = diff/(1000*60);
                } else if ("H".equals(type)) {
                    sec = diff/(1000*60*60);
                } else if ("D".equals(type)) {
                    sec = diff/(1000*60*60*24);
                }
                System.out.println(sec);
            } catch (ParseException e) {
                logger.error("error", e);
            }

        }
        return sec;
    }
}
