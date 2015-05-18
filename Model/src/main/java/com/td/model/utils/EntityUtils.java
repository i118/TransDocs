package com.td.model.utils;

import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by konstantinchipunov on 05.12.14.
 */
public class EntityUtils {

    public static String buildPersonDescription(String firstName, String lastName, String patronymic){
        StringBuilder builder = new StringBuilder();
        if (!StringUtils.hasText(firstName) && !StringUtils.hasText(lastName)) return "";
        if(StringUtils.hasText(firstName) && !StringUtils.hasText(lastName)) return firstName;
        if(!StringUtils.hasText(firstName) && StringUtils.hasText(lastName)) return lastName;
        builder.append(lastName).append(" ").append(String.valueOf(firstName.charAt(0)).toUpperCase()).append(".");
        if (StringUtils.hasText(patronymic)) {
            builder.append(" ").append(String.valueOf(patronymic.charAt(0)).toUpperCase()).append(".");
        }
        return builder.toString();
    }
}
