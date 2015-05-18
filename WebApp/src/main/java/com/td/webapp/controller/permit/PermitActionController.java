package com.td.webapp.controller.permit;

import com.td.model.entity.IPersistent;
import com.td.service.context.qualifier.PermissionEvaluatorQualifier;
import com.td.service.permit.PermissionEvaluatorAdapter;
import com.td.webapp.controller.AbstractController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by konstantinchipunov on 01.08.14.
 */
@Controller
@RequestMapping(PermitActionController.CONTROLLER_NAME)
public class PermitActionController extends AbstractController {

    private PermissionEvaluatorAdapter permissionEvaluator;

    public static final String CONTROLLER_NAME = "PermitActionController";

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }

    public static class RequestName extends AbstractController.RequestName{
        public static final String IS_PERMIT_ACTION = "is.permit.action";
    }

    @RequestMapping(value = "/"+ RequestName.IS_PERMIT_ACTION, method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public @ResponseBody boolean isPermitAction(String actionName, @RequestBody(required = false) IPersistent targetObject){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       return getPermissionEvaluator().hasPermission(authentication, targetObject, actionName);
    }


    public PermissionEvaluatorAdapter getPermissionEvaluator() {
        return permissionEvaluator;
    }

    @Inject
    @PermissionEvaluatorQualifier
    public void setPermissionEvaluator(PermissionEvaluatorAdapter permissionEvaluator) {
        this.permissionEvaluator = permissionEvaluator;
    }
}
