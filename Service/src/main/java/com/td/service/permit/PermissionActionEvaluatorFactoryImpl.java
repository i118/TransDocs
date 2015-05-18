package com.td.service.permit;

import java.util.Collections;
import java.util.Map;

/**
 * Created by konstantinchipunov on 01.08.14.
 */
public class PermissionActionEvaluatorFactoryImpl implements PermissionActionEvaluatorFactory {

    private final Map<String, PermissionEvaluatorAdapter> evaluatorMap;

    public PermissionActionEvaluatorFactoryImpl(Map<String, PermissionEvaluatorAdapter> evaluatorMap) {
        this.evaluatorMap = Collections.unmodifiableMap(evaluatorMap);
    }

    @Override
    public PermissionEvaluatorAdapter getEvaluator(Object action) {
        return evaluatorMap.get(action);
    }
}
