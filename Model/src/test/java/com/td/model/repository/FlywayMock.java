package com.td.model.repository;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.FlywayException;

/**
 * Created by zerotul on 30.03.15.
 */
public class FlywayMock extends Flyway {

    @Override
    public int migrate() throws FlywayException {
        return 0;
    }

    @Override
    public void clean() {

    }

    @Override
    public void setSchemas(String... schemas) {

    }
}
