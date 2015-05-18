package com.td.model.repository.context;

import com.td.model.context.DataSourceContext;
import com.td.model.context.ModelAopContext;
import com.td.model.context.PersistenceRepositoryContext;
import com.td.model.context.CollectionRepositoryContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by zerotul on 03.04.15.
 */
@Configuration
@Import(
        {ModelAopContext.class, DataSourceContext.class, PersistenceRepositoryContext.class, CollectionRepositoryContext.class, ServiceTestContext.class}
)
@PropertySource("classpath:/config/model/test/propeties/DataSource.properties")
public class ModelTestContext {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
                return new PropertySourcesPlaceholderConfigurer();
        }

        @Bean
        ApplicationListener refreshListener(){
                return new RefreshListener();
        }
}
