package com.ais.util;

import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import org.apache.commons.lang.StringUtils;

/**
 * User: son.nguyen
 * Date: 11/18/13
 * Time: 9:56 AM
 */
public class AISDoubleConverter extends DoubleConverter {

    @Override
    public Object fromString(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return super.fromString(str);
        }
    }
}
