package com.td.model.utils;

import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * Created by konstantinchipunov on 13.08.14.
 */
public class StringUtil {

    public static String firstUpperCase(String value){
        if(!hasText(value)) return null;
        return value.substring(0, 1).toUpperCase(Locale.getDefault()) + value.substring(1);
    }

    public static boolean hasText(String value){
        return StringUtils.hasText(value);
    }
}
