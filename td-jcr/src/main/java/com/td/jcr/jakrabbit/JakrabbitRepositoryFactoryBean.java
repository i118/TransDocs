package com.td.jcr.jakrabbit;

import com.td.jcr.AbstractRepositoryFactoryBean;
import org.apache.jackrabbit.api.JackrabbitRepository;
import org.apache.jackrabbit.core.RepositoryImpl;
import org.apache.jackrabbit.core.config.RepositoryConfig;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.InputSource;

import javax.jcr.Repository;
import java.net.URI;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public class JakrabbitRepositoryFactoryBean extends AbstractRepositoryFactoryBean{

    private static final String DEFAULT_CONF_FILE = "repository.xml";

    private RepositoryConfig repositoryConfig;

    private URI homeDir;

    @Override
    protected void resolveConfigurationResource() throws Exception {
        if (repositoryConfig != null)
            return;

        if (this.configuration == null) {
            if (log.isDebugEnabled())
                log.debug("no configuration resource specified, using the default one:" + DEFAULT_CONF_FILE);
            configuration = new ClassPathResource(DEFAULT_CONF_FILE);
        }

        if (homeDir == null) {
            if (log.isDebugEnabled())

            throw new  IllegalStateException("repository home directory not found");
        }

        repositoryConfig = RepositoryConfig.create(new InputSource(configuration.getInputStream()),
                homeDir.getPath());
    }

    @Override
    protected Repository createRepository() throws Exception {
        log.debug(repository);
        return RepositoryImpl.create(repositoryConfig);
    }

    @Override
    public void destroy() throws Exception {
        log.debug("repository shutdown");
        if (repository instanceof JackrabbitRepository)
            log.debug("repository shutdown");
            ((JackrabbitRepository) repository).shutdown();
    }

    public RepositoryConfig getRepositoryConfig() {
        return repositoryConfig;
    }

    public void setRepositoryConfig(RepositoryConfig repositoryConfig) {
        this.repositoryConfig = repositoryConfig;
    }

    public URI getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(URI homeDir) {
        this.homeDir = homeDir;
    }
}
