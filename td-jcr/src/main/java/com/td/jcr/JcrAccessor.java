package com.td.jcr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;

import javax.jcr.RepositoryException;
import java.io.IOException;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public abstract class JcrAccessor implements InitializingBean {

    protected final Log logger = LogFactory.getLog(getClass());

    private SessionFactory sessionFactory;

    public void afterPropertiesSet() {
        if (getSessionFactory() == null) {
            throw new IllegalArgumentException("sessionFactory is required");
        }
    }


    public DataAccessException convertJcrAccessException(RepositoryException ex) {
        return SessionFactoryUtils.translateException(ex);
    }

    public DataAccessException convertJcrAccessException(IOException ex) {
        return SessionFactoryUtils.translateException(ex);
    }

    public RuntimeException convertJcrAccessException(RuntimeException ex) {
        return ex;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

