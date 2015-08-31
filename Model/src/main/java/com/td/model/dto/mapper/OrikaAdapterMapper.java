package com.td.model.dto.mapper;

import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.entity.AbstractModel;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by zerotul.
 */
@Component
public class OrikaAdapterMapper implements Mapper<DirtySupportDTO, AbstractModel> {

    private final MapperFacade facade;

    @Inject
    public OrikaAdapterMapper(MapperFacade facade) {
        this.facade = facade;
    }

    @Override
    public void map(DirtySupportDTO src, AbstractModel dest) {
        Class<? extends AbstractModel> destClass = dest.getClass();
        DTO dto = src.getClass().getAnnotation(DTO.class);
        if(dto!=null){
            destClass = dto.mappedBy();
        }
        Type destType = TypeFactory.valueOf(destClass);
        Type srcType = TypeFactory.valueOf(src.getClass());
        facade.map(src, dest, srcType, destType);
    }

    @Override
    public <V extends AbstractModel> V map(Object src, Class<V> clazz) {
        return facade.map(src, clazz);
    }
}
