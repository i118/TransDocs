package com.td.model.factory;

import com.td.model.entity.IPersistent;

/**
 * Created by konstantinchipunov on 22.09.14.
 */
public interface ITypeFactory {

    public Class getClassByType(String type);

    public <T extends IPersistent> T createObjectByType(String type);
}
