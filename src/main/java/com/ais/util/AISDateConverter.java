package com.ais.util;

import com.thoughtworks.xstream.converters.basic.DateConverter;
import org.apache.commons.lang.StringUtils;

/**
 * User: son.nguyen
 * Date: 11/18/13
 * Time: 9:56 AM
 */
public class AISDateConverter extends DateConverter {

    @Override
    public Object fromString(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return super.fromString(str);
        }
    }
}
