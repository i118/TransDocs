package com.td.model.multitenant;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zerotul on 23.03.15.
 */
public class SchemaProviderImpl implements SchemaProvider{

    private static ThreadLocal<String> currentSchema = new ThreadLocal<>();

    private JdbcTemplate jdbcTemplate;

    private String schemaQuery;

    public SchemaProviderImpl(JdbcTemplate jdbcTemplate, String schemaQuery) {
        this.jdbcTemplate = jdbcTemplate;
        this.schemaQuery = schemaQuery;
    }

    public List<String> getSchemas(){
       return jdbcTemplate.queryForList(schemaQuery, String.class);
    }

    public static String currentSchema() {
        return currentSchema.get();
    }

    public static void setCurrentSchema(String schema) {
        currentSchema.set(schema);
    }
}
