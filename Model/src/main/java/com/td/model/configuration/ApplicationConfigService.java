package com.td.model.configuration;

import java.util.Properties;

/**
 * Created by konstantinchipunov on 24.08.14.
 */
public class ApplicationConfigService {

    private final Properties applicationProperties;


    public static class PropertyNames{
        public static final String IS_EMBEDDED_DB = "isEmbeddedDB";
    }

    public ApplicationConfigService(Properties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public boolean isEmbeddedDB(){
        return Boolean.parseBoolean(applicationProperties.getProperty(PropertyNames.IS_EMBEDDED_DB));
    }
}
