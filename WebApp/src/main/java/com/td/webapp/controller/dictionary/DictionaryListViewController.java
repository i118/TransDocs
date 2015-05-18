package com.td.webapp.controller.dictionary;

import com.td.model.entity.dictionary.DictionaryModel;
import com.td.model.repository.SectionRepository;
import com.td.service.context.qualifier.DictionaryServiceQualifier;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.controller.AbstractListViewController;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by konstantinchipunov on 02.01.14.
 */
@Controller
@RequestMapping("/"+DictionaryListViewController.CONTROLLER_NAME)
public class DictionaryListViewController extends AbstractController{

    public static final String CONTROLLER_NAME = "DictionaryListViewController";

    private SectionRepository<DictionaryModel> dictionaryService;

    public class RequestName extends AbstractListViewController.RequestName{}

    @RequestMapping(value = "/" + RequestName.GET_CONTENT)
    protected @ResponseBody
    IResponse getContent() {
        IResponse response = new ResponseImpl();
        List<DictionaryModel> models = getDictionaryService().findAll();
        response.setSuccess(true);
        response.addResults(models);
        return response;
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }

    public SectionRepository<DictionaryModel> getDictionaryService() {
        return dictionaryService;
    }

    @Inject
    @DictionaryServiceQualifier
    public void setDictionaryService(SectionRepository<DictionaryModel> dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
}
