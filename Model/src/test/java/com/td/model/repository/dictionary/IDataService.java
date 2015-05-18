package com.td.model.repository.dictionary;

import com.td.model.entity.IPersistent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by konstantinchipunov on 28.07.14.
 */
public interface IDataService<T extends IPersistent> {

    public T[][] getDataArray();


    public default Collection<T> getDataCollection(){
        T[][] objects = getDataArray();
        Set<T> modelSet = new HashSet<>();
        for(T[] model : objects){
            modelSet.add(model[0]);
        }
        return modelSet;
    }

}
