package com.td.model.dto.mapper.orika;

import ma.glasnost.orika.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by zerotul.
 */
@Component
public class CustomMapperFactoryImpl implements CustomMapperFactory {

    private final Set<Mapper> customMappers;

    private Map<String, NamedCustomMapper> namedMappers;

    @Inject
    public CustomMapperFactoryImpl(Set<Mapper> customMappers) {
        this.customMappers = customMappers;
        this.namedMappers = new HashMap<>();
    }

    @PostConstruct
    private void init(){
        Iterator<Mapper> it = customMappers.iterator();
        while (it.hasNext()){
            Mapper mapper = it.next();
            if(mapper instanceof NamedCustomMapper){
                NamedCustomMapper namedCustomMapper = (NamedCustomMapper) mapper;
                Objects.requireNonNull(namedCustomMapper.getName());
                if(namedMappers.containsKey(namedCustomMapper.getName())) throw new IllegalStateException("duplication mapper named "+namedCustomMapper.getName());
                namedMappers.put(namedCustomMapper.getName(), namedCustomMapper);
                it.remove();
            }
        }
    }

    @Override
    public NamedCustomMapper getNamedMapper(String name) {
        return namedMappers.get(name);
    }

    @Override
    public Collection<Mapper> getCustomMappers() {
        return customMappers;
    }

}
