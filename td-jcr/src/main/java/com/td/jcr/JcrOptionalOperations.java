package com.td.jcr;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public interface JcrOptionalOperations extends JcrModel2Operations {

    public void addLockToken(String lock);

    public String[] getLockTokens();

    public void removeLockToken(String lt);

}