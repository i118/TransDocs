package com.td.webapp.controller.document;

import com.td.model.entity.document.AbstractDocumentModel;
import com.td.model.entity.document.dataset.DocumentDataSet;
import com.td.model.utils.PagingList;
import com.td.service.crud.document.DocumentService;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.PagingResponse;
import com.td.webapp.response.PagingResponseImpl;
import org.springframework.web.bind.annotation.*;
import org.zerotul.specification.Specification;

/**
 * Created by zerotul on 28.01.15.
 */
public abstract class AbstractDocumentController<T extends AbstractDocumentModel> extends AbstractController{

    public static final class RequestName extends AbstractController.RequestName{
        public static final String FIND_DATA_SET = "find.dataset";
    }

    @RequestMapping(value = "/"+ RequestName.FIND_DATA_SET, method = RequestMethod.POST,
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse findDataSet(@RequestBody Specification<? super DocumentDataSet> specification){
        PagingList<DocumentDataSet> documents = getDocumentService().findDocumentDataSet(specification);
        PagingResponse response = new PagingResponseImpl(documents.getTotalCount());
        response.addResults(documents);
        response.setSuccess(true);
        return response;
    }

    protected abstract DocumentService<T> getDocumentService();

}
