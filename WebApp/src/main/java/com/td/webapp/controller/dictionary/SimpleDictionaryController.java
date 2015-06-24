package com.td.webapp.controller.dictionary;

import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.repository.dictionary.DictionaryJPARepository;
import com.td.model.entity.dictionary.SimpleDictionary;
import com.td.service.context.qualifier.DictionaryCrud;
import com.td.service.crud.dictionary.DictionaryCRUDService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zerotul on 08.04.15.
 */
@Controller
@RequestMapping("/"+SimpleDictionaryController.CONTROLLER_NAME)
public class SimpleDictionaryController extends AbstractDictionaryController<SimpleDictionary> {

    public static final String CONTROLLER_NAME = "SimpleDictionary";


    @Inject
    @DictionaryCrud
    private DictionaryCRUDService<SimpleDictionary, DictionaryJPARepository<SimpleDictionary>> dictionaryService;

    @Override
    public DictionaryCRUDService<SimpleDictionary, DictionaryJPARepository<SimpleDictionary>> getDictionaryService() {
        return dictionaryService;
    }

    @Override
    public void deleteDictionary(SimpleDictionary persistent, Map<String, String> arguments) {
        dictionaryService.deleteDictionaryObject(persistent, arguments);
    }

    @Override
    public void createDictionary(SimpleDictionary persistent, Map<String, String> arguments) {
        dictionaryService.createDictionaryObject(persistent, arguments);
    }

    @Override
    public void updateDictionary(SimpleDictionary persistent, Map<String, String> arguments) {
         dictionaryService.updateDictionaryObject(persistent, arguments);
    }

    @Override
    public SimpleDictionary getDictionary(UUID persistentId, Map<String, String> arguments) {
        return dictionaryService.getModel(persistentId);
    }



    @Override
    public String getControllerName() {
        return null;
    }
}
