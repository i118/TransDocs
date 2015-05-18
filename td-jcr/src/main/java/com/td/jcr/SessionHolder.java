package com.td.jcr;

import org.springframework.transaction.support.ResourceHolderSupport;

import javax.jcr.Session;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public class SessionHolder extends ResourceHolderSupport {

    private Session session;

    public SessionHolder(Session session) {
        setSession(session);
    }

    protected void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}