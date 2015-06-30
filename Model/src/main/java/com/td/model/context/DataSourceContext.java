package com.td.model.context;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.googlecode.flyway.core.Flyway;
import com.td.jcr.*;
import com.td.jcr.jca.XAConnectionManager;
import com.td.model.configuration.DataSourceConfigService;
import com.td.model.multitenant.FlywayMultiTenantMigration;
import com.td.model.multitenant.SchemaAwareDataSourceProxy;
import com.td.model.multitenant.SchemaProvider;
import com.td.model.multitenant.SchemaProviderImpl;
import org.apache.commons.dbcp2.managed.BasicManagedDataSource;
import org.apache.jackrabbit.jca.JCAManagedConnectionFactory;
import org.hibernate.cfg.*;
import org.hibernate.internal.SessionFactoryImpl;
import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.jcr.Repository;
import javax.jcr.SimpleCredentials;
import javax.persistence.EntityManagerFactory;
import javax.resource.spi.ManagedConnectionFactory;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
public class DataSourceContext {

    @Bean
    public JndiTemplate jndiTemplate(){
        return new JndiTemplate();
    }

    @Bean
    @Lazy
    protected PGXADataSource pgxaDataSource(
            @Value("${dbPortNumber}")String portNumber,
            @Value("${dbName}")String databaseName,
            @Value("${jdbc.username}")String user,
            @Value("${jdbc.password}")String password,
            @Value("${dbServerName}")String serverName
    ){
        PGXADataSource xaDataSource = new PGXADataSource();
        try {
            xaDataSource.setPortNumber(Integer.parseInt(portNumber));
        }catch(NumberFormatException e){
            throw new IllegalStateException("incorrect value in property /config/model/propeties/DataSource.properties#dbPortNumber value="+portNumber);
        }
        xaDataSource.setDatabaseName(databaseName);
        xaDataSource.setUser(user);
        xaDataSource.setPassword(password);
        xaDataSource.setServerName(serverName);
        return xaDataSource;
    }

    @Bean
    @Lazy
    protected DataSource alternativeDataSource(PGXADataSource pgxaDataSource, TransactionManager arjunaTransactionManager){
        BasicManagedDataSource dataSource = new BasicManagedDataSource();
        dataSource.setXaDataSourceInstance(pgxaDataSource);
        dataSource.setTransactionManager(arjunaTransactionManager);
        dataSource.setMaxTotal(50);
        dataSource.setInitialSize(1);
        return dataSource;
    }

    @Bean(name = "jndiDataSource")
    @Lazy
    protected JndiObjectFactoryBean jndiDataSourceFactory(@Qualifier("alternativeDataSource")DataSource alternativeDataSource){
        JndiObjectFactoryBean proxyFactoryBean = new JndiObjectFactoryBean();
        proxyFactoryBean.setJndiTemplate(jndiTemplate());
        proxyFactoryBean.setJndiName("PGXADataSource");
        proxyFactoryBean.setDefaultObject(alternativeDataSource);
        return proxyFactoryBean;
    }

    @Bean
    protected  DataSource dataSource(@Qualifier("jndiDataSource")JndiObjectFactoryBean jndiDataSourceFactory){
       return new SchemaAwareDataSourceProxy((DataSource) jndiDataSourceFactory.getObject());
    }

    @Bean
    @Lazy
    public SchemaProvider schemaProvider(@Qualifier("jndiDataSource") JndiObjectFactoryBean jndiDataSourceFactory){
        return new SchemaProviderImpl(new JdbcTemplate((DataSource) jndiDataSourceFactory.getObject()), "SELECT nspname FROM pg_catalog.pg_namespace WHERE nspname LIKE 'td_%'");
    }

    @Bean
    @Lazy
    Flyway flyway(@Qualifier("jndiDataSource")JndiObjectFactoryBean jndiDataSourceFactory){
        Flyway flyway =  new Flyway();
        flyway.setDataSource((DataSource) jndiDataSourceFactory.getObject());
        flyway.setLocations("classpath:db/app");
        flyway.setEncoding("UTF-8");
        return flyway;
    }

    @Bean
    @Lazy
    Flyway adminFlyway(@Qualifier("jndiDataSource")JndiObjectFactoryBean jndiDataSourceFactory){
        Flyway flyway =  new Flyway();
        flyway.setDataSource((DataSource) jndiDataSourceFactory.getObject());
        flyway.setLocations("classpath:db/admin");
        flyway.setEncoding("UTF-8");
        flyway.setSchemas("td_admin");
        return flyway;
    }

    @Bean
    @Lazy
    protected  FlywayMultiTenantMigration flywayMultiTenantMigration(
            @Qualifier("flyway")Flyway flyway,
            @Qualifier("adminFlyway")Flyway adminFlyway,
            SchemaProvider schemaProvider
    ){
       return new FlywayMultiTenantMigration(flyway, adminFlyway, schemaProvider);
    }

    @Bean
    @DependsOn({"transactionManager", "flywayMultiTenantMigration", "springContextHolder"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql}") String showSql,
            @Value("${hibernate.jtaPlatformClass}") String jpaPlatformClass,
            @Value("${encoding}") String dbEncoding,
            @Qualifier("dataSource")DataSource dataSource
    ){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceUnitName("TDPersistenceUnit");
        HibernateJpaVendorAdapter adapter =  new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform(dialect);
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setPackagesToScan("com.td.model.entity*");
        Properties jpaProp = new Properties();
        jpaProp.put("hibernate.dialect", dialect);
        jpaProp.put("hibernate.query.factory_class", "org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory");
        jpaProp.put("hibernate.hbm2ddl.auto", "none");
        jpaProp.put("hibernate.show_sql", Boolean.parseBoolean(showSql));
        jpaProp.put("hibernate.connection.charset", dbEncoding);
        jpaProp.put("hibernate.connection.release_mode", "auto");
        jpaProp.put("javax.persistence.validation.mode", "callback");
        jpaProp.put("hibernate.transaction.jta.platform", jpaPlatformClass);
        factoryBean.setJpaProperties(jpaProp);

        Map<String, Object> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.put("hibernate.current_session_context_class", "jta");
        jpaPropertyMap.put("javax.persistence.transactionType", "JTA");
        jpaPropertyMap.put("javax.persistence.validation.factory", localValidatorFactoryBean());
        jpaPropertyMap.put("javax.persistence.validation.group.pre-persist", "com.td.model.validation.group.PrePersistGroup,javax.validation.groups.Default");
        factoryBean.setJpaPropertyMap(jpaPropertyMap);
        return factoryBean;
    }

    @Bean
    public Hibernate4Module hibernateJacksonModule(LocalContainerEntityManagerFactoryBean entityManagerFactory){
        Hibernate4Module module = new Hibernate4Module(entityManagerFactory.getNativeEntityManagerFactory().unwrap(SessionFactoryImpl.class));
        module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
        module.enable(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        return module;
    }

    @Bean
    public  LocalValidatorFactoryBean localValidatorFactoryBean(){
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor(){
       return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public PlatformTransactionManager transactionManager(UserTransaction userTransaction, TransactionManager arjunaTransactionManager) throws SystemException {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager(userTransaction, arjunaTransactionManager);
        jtaTransactionManager.setDefaultTimeout(300);
        return jtaTransactionManager;
    }

    @Bean
    public JndiObjectFactoryBean arjunaTransactionManager() throws SystemException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiTemplate(jndiTemplate());
        factoryBean.setJndiName("jboss/TransactionManager");
        factoryBean.setDefaultObject(alternativeTransactionManager());
        return factoryBean;
    }

    @Bean
    @Lazy
    public TransactionManager alternativeTransactionManager() throws SystemException {
       TransactionManager transactionManager = com.arjuna.ats.jta.TransactionManager.transactionManager();
        transactionManager.setTransactionTimeout(300);
        return transactionManager;
    }

    @Bean
    public JndiObjectFactoryBean userTransaction() throws SystemException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiTemplate(jndiTemplate());
        factoryBean.setJndiName("jboss/UserTransaction");
        factoryBean.setDefaultObject(alternativeUserTransaction());
        return factoryBean;
    }

    @Bean
    @Lazy
    public UserTransaction alternativeUserTransaction() throws SystemException {
        UserTransaction userTransaction = com.arjuna.ats.jta.UserTransaction.userTransaction();
        userTransaction.setTransactionTimeout(300);
        return userTransaction;
    }



    @Bean
    public DataSourceConfigService dataSourceConfigService(
            @Value("${dbName}")String databaseName,
            @Value("${encoding}") String dbEncoding,
            @Value("${jdbc.url}") String dataBaseUrl
    ){
        Properties properties = new Properties();
        properties.put("dbName", databaseName);
        properties.put("encode", dbEncoding);
        properties.put("jdbsUrl", dataBaseUrl);
        return new DataSourceConfigService(properties);
    }


}
