package com.td.model.context;

import com.td.jcr.JcrFactory;
import com.td.jcr.JcrSessionFactory;
import com.td.jcr.JcrTemplate;
import com.td.jcr.jca.XAConnectionManager;
import org.apache.jackrabbit.jca.JCAManagedConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jca.support.LocalConnectionFactoryBean;

import javax.jcr.Repository;
import javax.jcr.SimpleCredentials;
import javax.resource.spi.ManagedConnectionFactory;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

/**
 * Created by zerotul.
 */
@Configuration
public class JCRContext {
    @Bean
    public LocalConnectionFactoryBean jcrRepository(ManagedConnectionFactory connectionFactory, TransactionManager arjunaTransactionManager) throws SystemException {
        LocalConnectionFactoryBean factoryBean = new LocalConnectionFactoryBean();
        factoryBean.setManagedConnectionFactory(connectionFactory);
        factoryBean.setConnectionManager(new XAConnectionManager(arjunaTransactionManager));
        return factoryBean;
    }

    @Bean
    public JCAManagedConnectionFactory jcaManagedConnectionFactory(@Value("${jcr.repoHomeDir}")String homeDir){
        JCAManagedConnectionFactory connectionFactory = new  JCAManagedConnectionFactory();
        connectionFactory.setConfigFile("classpath:/config/model/propeties/repository.xml");
        connectionFactory.setHomeDir(homeDir);
        connectionFactory.setBindSessionToTransaction(true);
        return connectionFactory;
    }

    @Bean
    public JcrSessionFactory jcrSessionFactory(Repository repository,
                                               @Value("${jcr.login}") String login,
                                               @Value("${jcr.password}") String pass,
                                               TransactionManager arjunaTransactionManager) throws SystemException {
        JcrSessionFactory sessionFactory = new JcrSessionFactory();
        sessionFactory.setTransactionManager(arjunaTransactionManager);
        sessionFactory.setRepository(repository);
        sessionFactory.setCredentials(new SimpleCredentials(login, pass.toCharArray()));
        return sessionFactory;
    }

    @Bean
    public JcrTemplate jcrTemplate(JcrSessionFactory sessionFactory){
        JcrTemplate jcrTemplate = new JcrTemplate();
        jcrTemplate.setAllowCreate(true);
        jcrTemplate.setSessionFactory(sessionFactory);
        return jcrTemplate;
    }

    @Bean
    public JcrFactory jcrFactory(Repository repository, JcrSessionFactory sessionFactory, JcrTemplate template){
        JcrFactory factory = JcrFactory.getInstance();
        factory.setRepository(repository);
        factory.setOperations(template);
        factory.setSessionFactory(sessionFactory);
        return factory;
    }
}
