package com.td.jcr;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.tika.Tika;
import org.springframework.core.CollectionFactory;
import org.springframework.dao.DataAccessException;
import org.xml.sax.ContentHandler;

import javax.jcr.Item;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFactory;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public class JcrTemplate extends JcrAccessor implements JcrOperations {

    private boolean allowCreate = false;

    private boolean exposeNativeSession = false;

    public JcrTemplate() {
    }

    public JcrTemplate(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        afterPropertiesSet();
    }

    // InitializingBean methods
    public Object execute(JcrCallback action, boolean exposeNativeSession) throws DataAccessException {
        Session session = getSession();
        boolean existingTransaction = SessionFactoryUtils.isSessionThreadBound(session, getSessionFactory());
        if (existingTransaction) {
            logger.debug("Found thread-bound Session for JcrTemplate");
        }

        try {
            Session sessionToExpose = (exposeNativeSession ? session : createSessionProxy(session));
            Object result = action.doInJcr(sessionToExpose);
            // TODO: does flushing (session.refresh) should work here?
            // flushIfNecessary(session, existingTransaction);
            return result;
        }
        catch (RepositoryException ex) {
            throw convertJcrAccessException(ex);
            // IOException are not converted here
        }
        catch (IOException ex) {
            // use method to decouple the static call
            throw convertJcrAccessException(ex);
        }
        catch (RuntimeException ex) {
            // Callback code threw application exception...
            throw convertJcrAccessException(ex);
        }
        finally {
//            if (existingTransaction) {
//                logger.debug("Not closing pre-bound Jcr Session after JcrTemplate");
//            }
//            else {
//                SessionFactoryUtils.releaseSession(session, getSessionFactory());
//            }
        }
    }


    public Object execute(JcrCallback callback) throws DataAccessException {
        return execute(callback, isExposeNativeSession());
    }

    protected Session getSession() {
        return SessionFactoryUtils.getSession(getSessionFactory(), allowCreate);
    }

    // -------------------------------------------------------------------------
    // Convenience methods for loading individual objects
    // -------------------------------------------------------------------------


    public void addLockToken(final String lock) {
        execute((Session session)->{
                session.getWorkspace().getLockManager().addLockToken(lock);
                return null;
        }, true);
    }

    public Object getAttribute(final String name) {
        return execute((Session session)-> {
                return session.getAttribute(name);
        }, true);
    }

    public String[] getAttributeNames() {
        return (String[]) execute((Session session)-> {
                return session.getAttributeNames();
        }, true);
    }

    public ContentHandler getImportContentHandler(final String parentAbsPath, final int uuidBehavior) {
        return (ContentHandler) execute((Session session)-> {
                return session.getImportContentHandler(parentAbsPath, uuidBehavior);
        }, true);
    }

    public Item getItem(final String absPath) {
        return (Item) execute((Session session)-> {
                return session.getItem(absPath);
        }, true);
    }

    public String[] getLockTokens() {
        return (String[]) execute((Session session)-> {
                return session.getWorkspace().getLockManager().getLockTokens();
        }, true);
    }

    public String getNamespacePrefix(final String uri) {
        return (String) execute((Session session)-> {
                return session.getNamespacePrefix(uri);
        }, true);
    }

    public String[] getNamespacePrefixes() {
        return (String[]) execute((Session session)-> {
                return session.getNamespacePrefixes();
        }, true);
    }

    public String getNamespaceURI(final String prefix) {
        return (String) execute((Session session)-> {
                return session.getNamespaceURI(prefix);
        }, true);
    }

    public Node getNodeByIdentifier(final String uuid) {
        return (Node) execute((Session session)-> {
                return session.getNodeByIdentifier(uuid);
        }, true);
    }

    public Node getRootNode() {
        return (Node) execute((Session session)-> {
                return session.getRootNode();
        }, true);
    }

    public String getUserID() {
        return (String) execute((Session session)-> {
                return session.getUserID();
        }, true);
    }

    public ValueFactory getValueFactory() {
        return (ValueFactory) execute((Session session)-> {
                return session.getValueFactory();
        }, true);
    }

    public boolean hasPendingChanges() {
        return ((Boolean) execute((Session session)->{
                return new Boolean(session.hasPendingChanges());
        }, true)).booleanValue();
    }

    public void importXML(final String parentAbsPath, final InputStream in, final int uuidBehavior) {
        execute((Session session)-> {

                try {
                    session.importXML(parentAbsPath, in, uuidBehavior);
                }
                catch (IOException e) {
                    throw new JcrSystemException(e);
                }
                return null;
        }, true);
    }

    public void refresh(final boolean keepChanges) {
        execute((Session session)-> {
                session.refresh(keepChanges);
                return null;
        }, true);
    }

    public void removeLockToken(final String lt) {
        execute((Session session)-> {
                session.getWorkspace().getLockManager().removeLockToken(lt);
                return null;
        }, true);
    }

    public void rename(final Node node, final String newName) {
        execute((Session session)-> {
                session.move(node.getPath(), node.getParent().getPath() + "/" + newName);
                return null;
        }, true);
    }

    public void setNamespacePrefix(final String prefix, final String uri) {
        execute((Session session)-> {
                session.setNamespacePrefix(prefix, uri);
                return null;
        }, true);
    }

    public boolean isLive() {
        return ((Boolean) execute((Session session)-> {
                return new Boolean(session.isLive());
        }, true)).booleanValue();
    }
    public boolean itemExists(final String absPath) {
        return ((Boolean) execute((Session session)-> {
                return new Boolean(session.itemExists(absPath));
        }, true)).booleanValue();
    }


    public void move(final String srcAbsPath, final String destAbsPath) {
        execute((Session session)-> {
                session.move(srcAbsPath, destAbsPath);
                return null;
        }, true);
    }

    public void save() {
        execute((Session session)->{
            session.save();
            return null;
        }, true);
    }

    @Override
    public Node putFile(Node parent, String name, String mimeType, InputStream in){
      return (Node) execute((Session session)->{
             return JcrUtils.putFile(parent, name, mimeType, in);
      }, true);
    }

    public InputStream readFile(Node node){
        return (InputStream) execute((Session session)->{
           return JcrUtils.readFile(node);
        }, true);
    }

    public void readFile(final Node node, final OutputStream out){
        execute((Session session)->{
           JcrUtils.readFile(node,out);
            return null;
        }, true);
    }

    public Node getOrCreateFolder(Node parent, String name){
        return (Node) execute((Session session)->{
           return JcrUtils.getOrAddFolder(parent, name);
        }, true);
    }

    public void remove(Node node){
        execute((Session session)->{
            session.removeItem(node.getPath());
            return null;
        });
    }

    public Node getOrCreateFolder( String name){
      return getOrCreateFolder(getRootNode(), name);
    }


    public String dump(final Node node) {

        return (String) execute((Session session)->{
                Node nd = node;

                if (nd == null)
                    nd = session.getRootNode();

                return dumpNode(nd);

        }, true);

    }

    /**
     * Recursive method for dumping a node. This method is separate to avoid the
     * overhead of searching and opening/closing JCR sessions.
     *
     * @param node
     * @return
     * @throws RepositoryException
     */
    protected String dumpNode(Node node) throws RepositoryException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(node.getPath());

        PropertyIterator properties = node.getProperties();
        while (properties.hasNext()) {
            Property property = properties.nextProperty();
            buffer.append(property.getPath() + "=");
            if (property.getDefinition().isMultiple()) {
                Value[] values = property.getValues();
                for (int i = 0; i < values.length; i++) {
                    if (i > 0) {
                        buffer.append(",");
                    }
                    buffer.append(values[i].getString());
                }
            }
            else {
                buffer.append(property.getString());
            }
            buffer.append("\n");
        }

        NodeIterator nodes = node.getNodes();
        while (nodes.hasNext()) {
            Node child = nodes.nextNode();
            buffer.append(dumpNode(child));
        }
        return buffer.toString();

    }

    public QueryResult query(final Node node) {

        if (node == null)
            throw new IllegalArgumentException("node can't be null");

        return (QueryResult) execute((Session session)-> {
                boolean debug = logger.isDebugEnabled();

                QueryManager manager = session.getWorkspace().getQueryManager();
                if (debug)
                    logger.debug("retrieved manager " + manager);

                Query query = manager.getQuery(node);
                if (debug)
                    logger.debug("created query " + query);

                return query.execute();
        }, true);
    }

    public QueryResult query(final String statement) {
        return query(statement, null);
    }

    public QueryResult query(final String statement, final String language) {

        if (statement == null)
            throw new IllegalArgumentException("statement can't be null");

        return (QueryResult) execute((Session session)-> {
                // check language
                String lang = language;
                if (lang == null)
                    lang = Query.JCR_SQL2;
                boolean debug = logger.isDebugEnabled();

                // get query manager
                QueryManager manager = session.getWorkspace().getQueryManager();
                if (debug)
                    logger.debug("retrieved manager " + manager);

                Query query = manager.createQuery(statement, lang);
                if (debug)
                    logger.debug("created query " + query);

                return query.execute();
        }, true);
    }


    public Map query(final List list) {
        return query(list, null, false);
    }


    public Map query(final List list, final String language, final boolean ignoreErrors) {
        if (list == null)
            throw new IllegalArgumentException("list can't be null");

        return (Map) execute((Session session)-> {

                // check language
                String lang = language;
                if (lang == null)
                    lang = Query.JCR_SQL2;
                boolean debug = logger.isDebugEnabled();


                Map map = CollectionFactory.createMap(LinkedHashMap.class, list.size());

                // get query manager
                QueryManager manager = session.getWorkspace().getQueryManager();
                if (debug)
                    logger.debug("retrieved manager " + manager);
                for (Iterator iter = list.iterator(); iter.hasNext();) {
                    String statement = (String) iter.next();

                    Query query = manager.createQuery(statement, lang);
                    if (debug)
                        logger.debug("created query " + query);

                    QueryResult result;
                    try {
                        result = query.execute();
                        map.put(statement, result);
                    }
                    catch (RepositoryException e) {
                        if (ignoreErrors)
                            map.put(statement, null);
                        else
                            throw convertJcrAccessException(e);
                    }
                }
                return map;
        }, true);
    }

    /**
     * @return Returns the allowCreate.
     */
    public boolean isAllowCreate() {
        return allowCreate;
    }

    /**
     * @param allowCreate The allowCreate to set.
     */
    public void setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
    }

    /**
     * Create a close-suppressing proxy for the given Jcr Session.
     *
     * @param session the Jcr Session to create a proxy for
     * @return the Session proxy
     * @see javax.jcr.Session#logout()
     */
    protected Session createSessionProxy(Session session) {
        return (Session) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Session.class},
                new LogoutSuppressingInvocationHandler(session));
    }

    private class LogoutSuppressingInvocationHandler implements InvocationHandler {

        private final Session target;

        public LogoutSuppressingInvocationHandler(Session target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Invocation on Session interface (or vendor-specific
            // extension) coming in...

            if (method.getName().equals("equals")) {
                // Only consider equal when proxies are identical.
                return (proxy == args[0] ? Boolean.TRUE : Boolean.FALSE);
            }
            else if (method.getName().equals("hashCode")) {
                // Use hashCode of session proxy.
                return new Integer(hashCode());
            }
            else if (method.getName().equals("logout")) {
                // Handle close method: suppress, not valid.
                return null;
            }

            // Invoke method on target Session.
            try {
                Object retVal = method.invoke(this.target, args);

                // TODO: watch out for Query returned
				/*
				 * if (retVal instanceof Query) { prepareQuery(((Query)
				 * retVal)); }
				 */
                return retVal;
            }
            catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
        }
    }

    public String getMimeType(final InputStream in){
        Tika tika = new Tika();
        try {
            return tika.detect(in);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Invalid MIME type: ", e);
        }
    }

    public Node getNodeByPath(final String path){
        return (Node) execute((Session session)->{
          return session.getNode(path);
        }, true);
    }

    public Node getOrCreateByPath(final String path, String nodeType){
        return (Node) execute((Session session)->{
         return JcrUtils.getOrCreateByPath(path, nodeType, session);
        }, true);
    }

    protected boolean isVersionable(Node node) throws RepositoryException {
        return node.isNodeType("mix:versionable");
    }

    /**
     * @return Returns the exposeNativeSession.
     */
    public boolean isExposeNativeSession() {
        return exposeNativeSession;
    }

    /**
     * @param exposeNativeSession The exposeNativeSession to set.
     */
    public void setExposeNativeSession(boolean exposeNativeSession) {
        this.exposeNativeSession = exposeNativeSession;
    }

}
