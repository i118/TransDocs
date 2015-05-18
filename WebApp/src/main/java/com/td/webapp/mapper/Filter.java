package com.td.webapp.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by konstantinchipunov on 02.12.14.
 */
public class Filter {

    private List<FilterInternal> filter;

    public Filter(String jsonFilter){
        filter = new ArrayList<>();
        filter.addAll(decodeFilters(jsonFilter));
    }

    public String findByProperty(String propertyName){
        Optional<FilterInternal> filterInternal = filter.stream().filter((FilterInternal filter)->propertyName.equals(filter.getProperty())).findFirst();
        if(filterInternal.get()!=null){
            return filterInternal.get().getValue();
        }
        return null;
    }

    private List<FilterInternal> decodeFilters(String jsonFilter){
        ObjectMapper mapper = new ObjectMapper();
        try {
          return mapper.readValue(jsonFilter, mapper.getTypeFactory().constructCollectionType(List.class, FilterInternal.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
