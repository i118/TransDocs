package com.td.webapp.controller.dictionary;

import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.entity.dictionary.Dictionary;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.utils.PagingList;
import com.td.service.crud.dictionary.DictionaryCRUDService;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.PagingResponseImpl;
import com.td.webapp.response.ResponseImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerotul.specification.Specification;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 13.03.14.
 */
public abstract class AbstractDictionaryController<T extends Dictionary> extends AbstractController {


    @RequestMapping(value = "/"+ RequestName.CREATE_OBJECT, method = RequestMethod.POST,
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse createObject(@RequestBody T persistent,@RequestParam Map<String, String> arguments){
        IResponse response = new ResponseImpl();
        createDictionary(persistent, arguments);
        response.setSuccess(true);
        response.addResult(persistent);
        return response;
    }

    @RequestMapping(value = "/"+ RequestName.UPDATE_OBJECT +"/{persistentId}", method = RequestMethod.PUT,
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse updateObject(@RequestBody T persistent,@RequestParam Map<String, String> args){
        IResponse response = new ResponseImpl();
        updateDictionary(persistent, args);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/"+ RequestName.GET_OBJECT +"/{persistentId}", method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse getObject(@PathVariable UUID persistentId,@RequestParam Map<String, String> args){
        IResponse response = new ResponseImpl();
        Dictionary dictionary = getDictionary(persistentId, args);
        response.addResult(dictionary);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/"+ RequestName.GET_OBJECT, method = RequestMethod.POST,
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse findObjects(@RequestBody Specification<DictionaryDataSet> specification){

        PagingList<DictionaryDataSet> dictionaries = findDictionaries(specification);
        IResponse response = new PagingResponseImpl(dictionaries.getTotalCount());
        response.addResults(dictionaries);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/"+ RequestName.DELETE_OBJECT +"/{persistentId}", method = RequestMethod.DELETE,
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse deleteObject(@PathVariable String persistentId, @RequestBody T persistent){
        deleteDictionary(persistent, new HashMap<String, String>());
        IResponse response = new ResponseImpl();
        response.setSuccess(true);
        return response;
    }

    public abstract DictionaryCRUDService<T> getDictionaryService();

    public abstract void deleteDictionary(T persistent, Map<String, String> arguments);

    public abstract void createDictionary(T persistent, Map<String, String> arguments);

    public abstract void updateDictionary(T persistent, Map<String, String> arguments);

    public abstract T  getDictionary(UUID persistentId, Map<String, String> arguments);


    public PagingList<DictionaryDataSet> findDictionaries(Specification<DictionaryDataSet> specification){
        return getDictionaryService().findDataSet(specification);
    }

}
