package com.td.webapp.controller.dictionary;

import com.td.model.dto.dictionary.DictionaryDTO;
import com.td.model.dto.dictionary.SimpleDictionaryDTO;
import com.td.model.entity.dictionary.SimpleDictionary;
import com.td.service.context.qualifier.DictionaryCRUDFacade;
import com.td.service.context.qualifier.DictionaryCrud;
import com.td.service.crud.CRUDFacade;
import com.td.service.crud.dictionary.DictionaryCRUDService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zerotul on 08.04.15.
 */
@Controller
@RequestMapping("/"+SimpleDictionaryController.CONTROLLER_NAME)
public class SimpleDictionaryController extends AbstractDictionaryController<SimpleDictionary, SimpleDictionaryDTO> {

    public static final String CONTROLLER_NAME = "SimpleDictionary";

    private CRUDFacade<SimpleDictionary, SimpleDictionaryDTO> facade;

    @Override
    public void deleteDictionary(UUID persistentId, Map<String, String> arguments) {
       getFacade().delete(persistentId, obtainArguments(arguments));
    }

    @Override
    public void createDictionary(SimpleDictionary persistent, Map<String, String> arguments) {
        getFacade().create(persistent, obtainArguments(arguments));
    }

    @Override
    public SimpleDictionary updateDictionary(SimpleDictionaryDTO persistent, Map<String, String> arguments) {
        return getFacade().update(persistent, obtainArguments(arguments));
    }


    @Override
    public SimpleDictionary getDictionary(UUID persistentId, Map<String, String> arguments) {
      return getFacade().findById(persistentId);
    }


    @Override
    public CRUDFacade<SimpleDictionary, SimpleDictionaryDTO> getFacade() {
        return facade;
    }

    @Inject
    @DictionaryCRUDFacade
    public void setFacade(CRUDFacade<SimpleDictionary, SimpleDictionaryDTO> facade) {
        this.facade = facade;
    }

    @Override
    public String getControllerName() {
        return null;
    }
}
