package com.td.model.factory;

import com.td.model.entity.Persistent;

/**
 * Created by konstantinchipunov on 22.09.14.
 */
public interface ITypeFactory {

    public Class getClassByType(String type);

    public <T extends Persistent> T createObjectByType(String type);
}
