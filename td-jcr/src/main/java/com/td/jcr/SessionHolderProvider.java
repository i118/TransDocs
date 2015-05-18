package com.td.jcr;

import javax.jcr.Session;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public interface SessionHolderProvider {

    /**
     * Return the specific session holder.
     *
     * @param session
     * @return
     */
    public SessionHolder createSessionHolder(Session session);

    /**
     * Method for maching the sessionHolderProvider against a repository (given by name).
     *
     * @param repositoryName
     * @return true if the sessionHolderProvider is suitable for the given repository name, false otherwise.
     */
    public boolean acceptsRepository(String repositoryName);
}
