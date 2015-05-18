package com.td.model.context;

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

}
