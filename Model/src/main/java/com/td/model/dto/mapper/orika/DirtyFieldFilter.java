package com.td.model.dto.mapper.orika;

import com.td.model.dto.DirtySupportDTO;
import ma.glasnost.orika.ClassMapFilter;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by zerotul.
 */
@Component
public class DirtyFieldFilter implements ClassMapFilter<DirtySupportDTO, Object> {
    @Override
    public boolean shouldMap(DirtySupportDTO source, Object destination, Object sourcePropertyValue, String sourcePropertyName, Object destinationPropertyValue, String destPropertyName, MappingContext context) {
        return source.isDirtyField(sourcePropertyName);
    }

    @Override
    public boolean filtersSource() {
        return true;
    }

    @Override
    public boolean filtersDestination() {
        return false;
    }
}
