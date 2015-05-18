package com.td.service.permit;

import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Created by konstantinchipunov on 01.08.14.
 */
public interface PermissionEvaluatorAdapter<T> {

    boolean hasPermission(Authentication authentication, T targetDomainObject, Object permission);

    boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission);
}
