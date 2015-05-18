package com.td.jcr;

import javax.jcr.Repository;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public interface SessionHolderProviderManager {
    public SessionHolderProvider getSessionProvider(Repository repository);
}
