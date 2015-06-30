package com.td.webapp.mapper;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.hibernate.cfg.Configuration;
import org.zerotul.specificaion.jackson.SpecificationModule;

import java.util.Collection;


/**
 * Created by konstantinchipunov on 15.08.14.
 */
public class CustomerObjectMapper extends ObjectMapper {


    public CustomerObjectMapper(Collection<Module> modules){
        modules.forEach((Module module)->{
            registerModule(module);
        });
    }
}
