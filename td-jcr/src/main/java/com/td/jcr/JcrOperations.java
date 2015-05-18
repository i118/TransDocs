package com.td.jcr;

import org.springframework.dao.DataAccessException;

import javax.jcr.Node;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public interface JcrOperations extends JcrOptionalOperations {

    public Object execute(JcrCallback action, boolean exposeNativeSession) throws DataAccessException;

    public Object execute(JcrCallback callback) throws DataAccessException;

    public String dump(Node node);


    public void rename(Node node, String newName);

    public String getMimeType(final InputStream in);

    public Node getNodeByPath(final String path);

    public Node getOrCreateFolder( String name);

    public Node getOrCreateFolder(Node parent, String name);

    public void readFile(Node node, OutputStream out);

    public InputStream readFile(Node node);

    public void remove(Node node);


    public Node getOrCreateByPath(final String path, String nodeType);
}