package com.td.model.context;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
@Import(
        {
                ModelAopContext.class,
                DataSourceContext.class,
                JCRContext.class,
                PersistenceRepositoryContext.class,
                CollectionRepositoryContext.class,
                ModelServiceContext.class
        }
)
@PropertySource("classpath:/config/model/propeties/DataSource.properties")
@ComponentScan("com.td.model.*")
public class ModelContext {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Module jsr310JakcsonModule(){
       return new JSR310Module();
    }

}
