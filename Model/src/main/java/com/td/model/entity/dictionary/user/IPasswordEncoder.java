package com.td.model.entity.dictionary.user;

/**
 * Created by konstantinchipunov on 28.07.14.
 */
public interface IPasswordEncoder {

    String encodePassword(String rawPass, Object salt);

    boolean isPasswordValid(String encPass, String rawPass, Object salt);

}
