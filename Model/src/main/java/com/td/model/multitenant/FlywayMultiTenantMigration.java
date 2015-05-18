package com.td.model.multitenant;

import com.googlecode.flyway.core.Flyway;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by zerotul on 24.03.15.
 */
public class FlywayMultiTenantMigration {

    private SchemaProvider schemaProvider;


    private Flyway flyway;
    private Flyway adminFlyway;

    public FlywayMultiTenantMigration(Flyway flyway, Flyway adminFlyway, SchemaProvider schemaProvider) {
        this.flyway = flyway;
        this.schemaProvider = schemaProvider;
        this.adminFlyway = adminFlyway;
    }

    @PostConstruct
    void migrate() {
        migrateAdminSchema();
        migrateAllSchemas();
    }

    private void migrateAllSchemas() {
        List<String> schemas = schemaProvider.getSchemas();
        for (String schema : schemas) {
            if(adminFlyway.getSchemas().length>0
                    && !adminFlyway.getSchemas()[0].equals(schema)) {
                flyway.setSchemas(schema);
                flyway.migrate();
            }
        }
    }

    private void migrateAdminSchema(){
        adminFlyway.migrate();
    }


}
