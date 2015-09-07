package com.td.model.dto.mapper.orika;

import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.Model;
import com.td.model.entity.Persistent;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by zerotul.
 */
@Component
public class OrikaDTOMapper extends CustomMapper<DirtySupportDTO, Object> {

    @PersistenceContext(name = "TDPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public void mapAtoB(DirtySupportDTO dirtySupportDTO, Object dest, MappingContext context) {
        Type<DirtySupportDTO> sourceType = TypeFactory.typeOf(dirtySupportDTO);
        Type destType = resolveDestType(sourceType, context);
        if(dest== null){
            mapperFacade.map(dirtySupportDTO, sourceType, destType, context);
        }else{
            if(dirtySupportDTO instanceof ModelDTO){
                ModelDTO modelDTO = (ModelDTO) dirtySupportDTO;
                if(!modelDTO.getObjectId().equals(((Persistent)dest).getObjectId())){
                    dest = entityManager.getReference(destType.getRawType(), modelDTO.getObjectId());
                }
            }
            mapperFacade.map(dirtySupportDTO, dest,sourceType, destType, context);
        }
    }


    private Type resolveDestType(Type<DirtySupportDTO> srcType, MappingContext context){
        Class<DirtySupportDTO> clazz = srcType.getRawType();
        DTO dto = clazz.getAnnotation(DTO.class);
        if(dto!=null){
            return TypeFactory.valueOf(dto.mappedBy());
        }
        return context.getResolvedDestinationType().getNestedType(0);
    }
}
