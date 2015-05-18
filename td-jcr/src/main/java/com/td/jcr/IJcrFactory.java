package com.td.jcr;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public interface IJcrFactory {

    public Repository getRepository();

    public Session getSession() throws RepositoryException;

    public JcrOperations getOperations();
}
