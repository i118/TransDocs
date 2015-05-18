package com.td.webapp.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.zerotul.specificaion.jackson.SpecificationModule;


/**
 * Created by konstantinchipunov on 15.08.14.
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper(){
        Hibernate4Module module = new Hibernate4Module();
        module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
        registerModule(module);
        registerModule(new JSR310Module());
        registerModule(new SpecificationModule());
    }
}
