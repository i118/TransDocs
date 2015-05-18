package com.td.jcr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.jcr.Credentials;
import javax.jcr.NamespaceRegistry;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.observation.ObservationManager;
import javax.transaction.TransactionManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public class JcrSessionFactory implements InitializingBean, DisposableBean, SessionFactory {
    private static final Log log = LogFactory.getLog(JcrSessionFactory.class);

    private Repository repository;

    private String workspaceName;

    private Credentials credentials;

    private EventListenerDefinition eventListeners[] = new EventListenerDefinition[]{};

    private Properties namespaces;

    private Map overwrittenNamespaces;

    private boolean forceNamespacesRegistration = false;

    private boolean keepNewNamespaces = true;

    private boolean skipExistingNamespaces = true;

    private TransactionManager transactionManager;

    private SessionHolderProviderManager sessionHolderProviderManager;


    private SessionHolderProvider sessionHolderProvider;

    public JcrSessionFactory(Repository repository, String workspaceName, Credentials credentials) {
        this(repository, workspaceName, credentials, null);
    }

    public JcrSessionFactory(Repository repository, String workspaceName, Credentials credentials,
                             SessionHolderProviderManager sessionHolderProviderManager) {
        this.repository = repository;
        this.workspaceName = workspaceName;
        this.credentials = credentials;
        this.sessionHolderProviderManager = sessionHolderProviderManager;
    }

    public JcrSessionFactory() {
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(getRepository(), "repository is required");

        if (eventListeners != null && eventListeners.length > 0 && !Utils.supportsObservation(getRepository()))
            throw new IllegalArgumentException("repository " + getRepositoryInfo()
                    + " does NOT support Observation; remove Listener definitions");

        registerNodeTypes();
        registerNamespaces();

        // determine the session holder provider
        if (sessionHolderProviderManager == null) {
            if (log.isDebugEnabled())
                log.debug("no session holder provider manager set; using the default one");
            sessionHolderProvider = new SessionHolderProviderImpl();
        } else
            sessionHolderProvider = sessionHolderProviderManager.getSessionProvider(getRepository());
    }


    protected void registerNodeTypes() throws Exception {

    }

    protected void unregisterNodeTypes() throws Exception {

    }

    protected void registerNamespaces() throws Exception {

        if (namespaces == null || namespaces.isEmpty())
            return;

        if (log.isDebugEnabled())
            log.debug("registering custom namespaces " + namespaces);

        NamespaceRegistry registry = getSession().getWorkspace().getNamespaceRegistry();


        String[] prefixes = registry.getPrefixes();

        Arrays.sort(prefixes);


        if (forceNamespacesRegistration) {


            if (!keepNewNamespaces)
                overwrittenNamespaces = new HashMap(namespaces.size());

            for (Iterator iter = namespaces.keySet().iterator(); iter.hasNext(); ) {
                String prefix = (String) iter.next();
                int position = Arrays.binarySearch(prefixes, prefix);
                if (position >= 0) {
                    if (log.isDebugEnabled()) {
                        log.debug("prefix " + prefix + " was already registered; unregistering it");
                    }
                    if (!keepNewNamespaces) {

                        overwrittenNamespaces.put(prefix, registry.getURI(prefix));
                    }
                    registry.unregisterNamespace(prefix);

                }
            }
        }


        for (Iterator iter = namespaces.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry namespace = (Map.Entry) iter.next();
            String prefix = (String) namespace.getKey();
            String ns = (String) namespace.getValue();

            int position = Arrays.binarySearch(prefixes, prefix);

            if (skipExistingNamespaces && position >= 0) {
                log.debug("namespace already registered under [" + prefix + "]; skipping registration");
            } else {
                log.debug("registering namespace [" + ns + "] under [" + prefix + "]");
                registry.registerNamespace(prefix, ns);
            }
        }
    }


    public void destroy() throws Exception {
        unregisterNamespaces();
        unregisterNodeTypes();

    }

    protected void unregisterNamespaces() throws Exception {

        if (namespaces == null || namespaces.isEmpty() || keepNewNamespaces)
            return;

        if (log.isDebugEnabled())
            log.debug("unregistering custom namespaces " + namespaces);

        NamespaceRegistry registry = getSession().getWorkspace().getNamespaceRegistry();

        for (Iterator iter = namespaces.keySet().iterator(); iter.hasNext(); ) {
            String prefix = (String) iter.next();
            registry.unregisterNamespace(prefix);
        }

        if (forceNamespacesRegistration) {
            if (log.isDebugEnabled())
                log.debug("reverting back overwritten namespaces " + overwrittenNamespaces);
            if (overwrittenNamespaces != null)
                for (Iterator iter = overwrittenNamespaces.entrySet().iterator(); iter.hasNext(); ) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    registry.registerNamespace((String) entry.getKey(), (String) entry.getValue());
                }
        }
    }


    public Session getSession() throws RepositoryException {
        return addListeners(repository.login(credentials, workspaceName));
    }


    public SessionHolder getSessionHolder(Session session) {
        return sessionHolderProvider.createSessionHolder(session);
    }

    protected Session addListeners(Session session) throws RepositoryException {
        if (eventListeners != null && eventListeners.length > 0) {
            Workspace ws = session.getWorkspace();
            ObservationManager manager = ws.getObservationManager();
            if (log.isDebugEnabled())
                log.debug("adding listeners " + Arrays.asList(eventListeners).toString() + " for session " + session);

            for (int i = 0; i < eventListeners.length; i++) {
                manager.addEventListener(eventListeners[i].getListener(), eventListeners[i].getEventTypes(),
                        eventListeners[i].getAbsPath(), eventListeners[i].isDeep(), eventListeners[i].getUuid(),
                        eventListeners[i].getNodeTypeName(), eventListeners[i].isNoLocal());
            }
        }
        return session;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }


    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }


    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof JcrSessionFactory)
            return (this.hashCode() == obj.hashCode());
        return false;

    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + repository.hashCode();
        // add the optional params (can be null)
        if (credentials != null)
            result = 37 * result + credentials.hashCode();
        if (workspaceName != null)
            result = 37 * result + workspaceName.hashCode();

        return result;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("SessionFactory for ");
        buffer.append(getRepositoryInfo());
        buffer.append("|workspace=");
        buffer.append(workspaceName);
        return buffer.toString();
    }

    public EventListenerDefinition[] getEventListeners() {
        return eventListeners;
    }

    public void setEventListeners(EventListenerDefinition[] eventListenerDefinitions) {
        this.eventListeners = eventListenerDefinitions;
    }

    private String getRepositoryInfo() {
        // in case toString() is called before afterPropertiesSet()
        if (getRepository() == null)
            return "<N/A>";

        StringBuffer buffer = new StringBuffer();
        buffer.append(getRepository().getDescriptor(Repository.REP_NAME_DESC));
        buffer.append(" ");
        buffer.append(getRepository().getDescriptor(Repository.REP_VERSION_DESC));
        return buffer.toString();
    }

    public Properties getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(Properties namespaces) {
        this.namespaces = namespaces;
    }

    protected SessionHolderProvider getSessionHolderProvider() {
        return sessionHolderProvider;
    }

    protected void setSessionHolderProvider(SessionHolderProvider sessionHolderProvider) {
        this.sessionHolderProvider = sessionHolderProvider;
    }

    public SessionHolderProviderManager getSessionHolderProviderManager() {
        return sessionHolderProviderManager;
    }

    public void setSessionHolderProviderManager(SessionHolderProviderManager sessionHolderProviderManager) {
        this.sessionHolderProviderManager = sessionHolderProviderManager;
    }

    public void setKeepNewNamespaces(boolean keepNamespaces) {
        this.keepNewNamespaces = keepNamespaces;
    }

    public void setForceNamespacesRegistration(boolean forceNamespacesRegistration) {
        this.forceNamespacesRegistration = forceNamespacesRegistration;
    }

    public void setSkipExistingNamespaces(boolean skipRegisteredNamespace) {
        this.skipExistingNamespaces = skipRegisteredNamespace;
    }

    public boolean isForceNamespacesRegistration() {
        return forceNamespacesRegistration;
    }

    public boolean isKeepNewNamespaces() {
        return keepNewNamespaces;
    }

    public boolean isSkipExistingNamespaces() {
        return skipExistingNamespaces;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
