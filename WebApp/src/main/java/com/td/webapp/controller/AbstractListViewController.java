package com.td.webapp.controller;

import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerotul.specification.Specification;

import java.util.List;

/**
 * Created by konstantinchipunov on 02.01.14.
 */
public abstract class AbstractListViewController extends AbstractController {

    public static class RequestName extends AbstractController.RequestName {
        public static final String GET_CONTENT = "get.content";
        public static final String FIND_OBJECTS = "find.objects";
    }


    @RequestMapping(value = "/" + RequestName.GET_CONTENT)
    protected @ResponseBody IResponse getContent(Specification<DictionaryDataSet> specification) {
        IResponse response = new ResponseImpl();
        List<DictionaryDataSet> models = getListViewContent(specification);
        response.setSuccess(true);
        response.addResults(models);
        return response;
    }


    protected abstract List<DictionaryDataSet> getListViewContent(Specification<DictionaryDataSet> specification);



}
