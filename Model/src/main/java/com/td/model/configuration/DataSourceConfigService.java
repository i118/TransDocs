package com.td.model.configuration;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 18.11.13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 *
 * Сервис содержащий инфу о базе
 */
public class DataSourceConfigService {

private Properties dataSourceProperties;

    public static class PropertyNames{
        public static final String DB_NAME = "dbName";

        public static final String JDBS_URL = "jdbsUrl";

        public static final String ENCODE = "encode";
    }

    public DataSourceConfigService(Properties dataSourceProperties){
        this.dataSourceProperties = dataSourceProperties;
    }


    public String getDbName() {
        return dataSourceProperties.getProperty(PropertyNames.DB_NAME);
    }

    public String getJdbcUrl() {
        return dataSourceProperties.getProperty(PropertyNames.JDBS_URL);
    }


    public String getEncode() {
        return dataSourceProperties.getProperty(PropertyNames.ENCODE);
    }

}
