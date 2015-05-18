package com.td.jcr;

import javax.jcr.Session;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public class SessionHolderProviderImpl implements SessionHolderProvider {

    public boolean acceptsRepository(String repositoryName) {
        return true;
    }

    public SessionHolder createSessionHolder(Session session) {
        return new SessionHolder(session);
    }

}

