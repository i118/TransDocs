package com.td.jcr;

import org.xml.sax.ContentHandler;

import javax.jcr.Item;
import javax.jcr.Node;
import javax.jcr.ValueFactory;
import javax.jcr.query.QueryResult;
import java.util.List;
import java.util.Map;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public interface JcrModel1Operations {

    public Object getAttribute(String name);

    public String[] getAttributeNames();

    public ContentHandler getImportContentHandler(String parentAbsPath, int uuidBehavior);

    public Item getItem(String absPath);

    public String getNamespacePrefix(String uri);

    public String[] getNamespacePrefixes();

    public String getNamespaceURI(String prefix);

    public Node getNodeByIdentifier(String uuid);

    public Node getRootNode();

    public String getUserID();


    public ValueFactory getValueFactory();

    public boolean isLive();

    public boolean itemExists(String absPath);

    public QueryResult query(Node node);

    public QueryResult query(String statement);

    public QueryResult query(String statement, String language);

    public Map query(final List list);

    public Map query(final List list, final String language, final boolean ignoreErrors);

}
