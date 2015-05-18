package com.td.jcr;

import javax.jcr.Node;
import java.io.InputStream;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public interface JcrModel2Operations extends JcrModel1Operations {

    public boolean hasPendingChanges();

    public void importXML(String parentAbsPath, InputStream in, int uuidBehavior);

    public void refresh(boolean keepChanges);

    public void setNamespacePrefix(String prefix, String uri);

    public void move(String srcAbsPath, String destAbsPath);

    public void save();

    public Node putFile(Node parent, String name, String mimeType,InputStream in) ;

}