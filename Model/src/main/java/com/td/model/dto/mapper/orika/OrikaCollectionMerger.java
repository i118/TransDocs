package com.td.model.dto.mapper.orika;

import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.Model;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by zerotul.
 */
@Component
public class OrikaCollectionMerger extends CustomMapper<Collection<ModelDTO>, Collection<Model>> {

    public void mapAtoB(Collection<ModelDTO> a, Collection<Model> b, MappingContext context) {

        Set<UUID> ids = new HashSet<>();
        Type<ModelDTO> sourceType = context.getResolvedSourceType().getNestedType(0);
        Type<Model> destType = context.getResolvedDestinationType().getNestedType(0);//resolveDestType(sourceType, context);
        for (ModelDTO dto : a) {
            Model bean = findBean(dto, b);
            if (bean != null) {
                mapperFacade.map(dto, bean, sourceType, destType, context);
            } else {
                Model model = mapperFacade.map(dto, sourceType, destType, context);
                b.add(model);
                ids.add(model.getObjectId());
            }
            ids.add(dto.getObjectId());
        }
        Iterator<Model> iterator = b.iterator();
        while (iterator.hasNext()) {
            Model model = iterator.next();
            if (!ids.contains(model.getObjectId())) {
                iterator.remove();
            }
        }
    }

    private Model findBean(ModelDTO modelDTO, Collection<Model> models) {
        for (Model model : models) {
            if (Objects.equals(modelDTO.getObjectId(), model.getObjectId())) {
                return model;
            }
        }
        return null;
    }

    private Type<Model> resolveDestType(Type<ModelDTO> srcType, MappingContext context){
        Class<ModelDTO> clazz = srcType.getRawType();
        DTO dto = clazz.getAnnotation(DTO.class);
        if(dto!=null){
            return TypeFactory.valueOf(dto.mappedBy());
        }
        return context.getResolvedDestinationType().getNestedType(0);
    }

}
