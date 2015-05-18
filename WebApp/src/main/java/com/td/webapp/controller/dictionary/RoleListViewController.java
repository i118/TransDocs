package com.td.webapp.controller.dictionary;

import com.td.model.context.qualifier.RoleQualifier;
import com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl;
import com.td.service.context.qualifier.RoleServiceQualifier;
import com.td.service.crud.dictionary.role.RoleService;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.controller.AbstractListViewController;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.List;

import static org.zerotul.specification.Specifications.from;

/**
 * Created by konstantinchipunov on 24.02.14.
 */
@Controller
@RequestMapping("/"+RoleListViewController.CONTROLLER_NAME)
public class RoleListViewController extends AbstractController {
    RoleService roleService;

    public static final String CONTROLLER_NAME = "RoleListViewController";

    public static class RequestName extends AbstractListViewController.RequestName{}

    @RequestMapping(value = "/" + RequestName.GET_CONTENT)
    protected @ResponseBody
    IResponse getContent() {
        Specification<DictionaryDataSetImpl> spec = from(DictionaryDataSetImpl.class).endFrom();
        IResponse response = new ResponseImpl();
        List<DictionaryDataSetImpl> models = getRoleService().findDataSet(spec);
        response.setSuccess(true);
        response.addResults(models);
        return response;
    }
    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }


    public RoleService getRoleService() {
        return roleService;
    }

    @Inject
    @RoleServiceQualifier
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
