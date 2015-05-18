package com.td.model.configuration;

import com.googlecode.flyway.core.Flyway;
import com.td.model.entity.dictionary.company.CompanyModel;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zerotul on 31.03.15.
 */
public class FlywayCompanyInstaller implements CompanyInstaller {
    public static final String COMPANY_SCHEMA_PREFIX = "td_";
    private final Flyway flyway;

    public FlywayCompanyInstaller(Flyway flyway) {
        this.flyway = flyway;
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public synchronized void install(CompanyModel company) {
        flyway.setSchemas(COMPANY_SCHEMA_PREFIX+company.getLogin());
        flyway.migrate();
    }
}
