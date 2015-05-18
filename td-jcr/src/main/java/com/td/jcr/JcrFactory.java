package com.td.jcr;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public class JcrFactory implements IJcrFactory {

    private Repository repository;

    private static volatile JcrFactory factory;

    private JcrSessionFactory sessionFactory;

    private JcrOperations operations;

    private JcrFactory() {
    }

    public static JcrFactory getInstance(){
        if(factory==null){
            synchronized (JcrFactory.class){
                if(factory==null){
                    factory = new JcrFactory();
                }
            }
        }
      return factory;
    }

    @Override
    public Repository getRepository() {
        return repository;
    }

    @Override
    public Session getSession() throws RepositoryException {
        return sessionFactory.getSession();
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public JcrSessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(JcrSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public JcrOperations getOperations() {
        return operations;
    }

    public void setOperations(JcrOperations operations) {
        this.operations = operations;
    }
}
