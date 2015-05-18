package com.td.webapp.controller;

import com.td.model.entity.journal.JournalModel;
import com.td.service.context.qualifier.JournalSectionQualifier;
import com.td.service.crud.SectionService;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zerotul on 19.03.15.
 */
@Controller
@RequestMapping("/"+JournalListViewController.CONTROLLER_NAME)
public class JournalListViewController extends AbstractController {

    public static final String CONTROLLER_NAME = "JournalListViewController";

    private SectionService<JournalModel> journalService;

    public class RequestName extends AbstractListViewController.RequestName{}

    @RequestMapping(value = "/" + RequestName.GET_CONTENT)
    protected @ResponseBody
    IResponse getContent() {
        IResponse response = new ResponseImpl();
        List<JournalModel> models = getJournalService().findAll();
        response.setSuccess(true);
        response.addResults(models);
        return response;
    }

    public SectionService<JournalModel> getJournalService() {
        return journalService;
    }

    @Inject
    @JournalSectionQualifier
    public void setJournalService(SectionService<JournalModel> journalService) {
        this.journalService = journalService;
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }
}
