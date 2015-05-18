package com.td.service.permit;

/**
 * Created by konstantinchipunov on 01.08.14.
 */
public interface PermissionActionEvaluatorFactory {

      public PermissionEvaluatorAdapter getEvaluator(Object actionName);
}
