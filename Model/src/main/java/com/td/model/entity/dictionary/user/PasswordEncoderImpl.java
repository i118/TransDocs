package com.td.model.entity.dictionary.user;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * Created by konstantinchipunov on 28.07.14.
 */
public class PasswordEncoderImpl implements IPasswordEncoder {

    static Logger logger = Logger.getLogger(PasswordEncoderImpl.class);

    private static volatile PasswordEncoderImpl encoderImpl;

    private PasswordEncoder encoder;

    public static PasswordEncoderImpl getInstance(){
       if(encoderImpl == null){
           synchronized (PasswordEncoderImpl.class){
               if(encoderImpl==null){
                   encoderImpl = new PasswordEncoderImpl();
               }
           }
       }
       return encoderImpl;
    }

    private PasswordEncoderImpl() {
    }

    @Override
    public String encodePassword(String rawPass, Object salt) {
        if(encoder!=null) {
            return encoder.encodePassword(rawPass, salt);
        }
        logger.warn("password not encoded");
        return rawPass;
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        if(encoder!=null) {
           return encoder.isPasswordValid(encPass, rawPass, salt);
        }
        logger.warn("password not encoded");
        return encPass.equals(rawPass);
    }

    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
