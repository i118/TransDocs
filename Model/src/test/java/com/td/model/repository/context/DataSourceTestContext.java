package com.td.model.repository.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zerotul on 03.04.15.
 */
//@Configuration
public class DataSourceTestContext  {

    @Bean
    protected DataSource dataSource() {
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        return databasePopulator;
    }

    private DataSource createDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory){
        EntityManagerFactory factory = entityManagerFactory.getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    @DependsOn ({"springContextHolder"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPersistenceUnitName("TDPersistenceUnit");
        HibernateJpaVendorAdapter adapter =  new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setPackagesToScan("com.td.model.entity*");
        Properties jpaProp = new Properties();
        jpaProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProp.put("hibernate.query.factory_class", "org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory");
        jpaProp.put("hibernate.hbm2ddl.auto", "update");
        jpaProp.put("hibernate.show_sql", Boolean.parseBoolean("true"));
        jpaProp.put("hibernate.connection.charset", "UTF-8");
        jpaProp.put("hibernate.connection.release_mode", "auto");
        jpaProp.put("javax.persistence.validation.mode", "callback");
        factoryBean.setJpaProperties(jpaProp);

        Map<String, Object> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.put("javax.persistence.validation.factory", localValidatorFactoryBean());
        jpaPropertyMap.put("javax.persistence.validation.group.pre-persist", "com.td.model.validation.group.PrePersistGroup,javax.validation.groups.Default");
        factoryBean.setJpaPropertyMap(jpaPropertyMap);
        return factoryBean;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor(){
        return new PersistenceAnnotationBeanPostProcessor();
    }
}
