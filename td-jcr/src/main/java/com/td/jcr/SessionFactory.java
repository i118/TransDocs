package com.td.jcr;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public interface SessionFactory {

    public Session getSession() throws RepositoryException;

    public SessionHolder getSessionHolder(Session session);
}
