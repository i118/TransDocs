package com.td.webapp.controller;

import com.td.model.entity.AdminSection;
import com.td.service.context.qualifier.AdminServiceQualifier;
import com.td.service.crud.SectionService;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zerotul on 25.03.15.
 */
@Controller
@RequestMapping("/"+ AdminController.CONTROLLER_NAME)
public class AdminController extends AbstractController {

    public static final String CONTROLLER_NAME = "AdminController";


    private SectionService<AdminSection> adminService;

    public static class RequestName extends AbstractController.RequestName {
        public static final String GET_SECTIONS = "get.sections";
    }


    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }

    @RequestMapping(value = "/" + RequestName.GET_SECTIONS)
    protected @ResponseBody
    IResponse getSections() {
        IResponse response = new ResponseImpl();
        List<AdminSection> sections = adminService.findAll();
        response.setSuccess(true);
        response.addResults(sections);
        return response;
    }

    public SectionService getAdminService() {
        return adminService;
    }

    @Inject
    @AdminServiceQualifier
    public void setAdminService(SectionService adminService) {
        this.adminService = adminService;
    }
}
