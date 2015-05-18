package com.td.jcr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import javax.jcr.Repository;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public abstract class AbstractRepositoryFactoryBean implements InitializingBean, DisposableBean, FactoryBean {
    protected final Log log = LogFactory.getLog(getClass());

    protected Resource configuration;

    protected Repository repository;

    protected abstract void resolveConfigurationResource() throws Exception;

    protected abstract Repository createRepository() throws Exception;

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public Object getObject() throws Exception {
        return this.repository;
    }

    @Override
    public Class<?> getObjectType() {
        return Repository.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        resolveConfigurationResource();
        repository = createRepository();
    }

    public Resource getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Resource configuration) {
        this.configuration = configuration;
    }
}
