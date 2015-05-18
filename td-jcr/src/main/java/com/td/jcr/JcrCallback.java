package com.td.jcr;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
@FunctionalInterface
public interface JcrCallback {

    public Object doInJcr(Session session) throws IOException, RepositoryException;
}
