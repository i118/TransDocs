package com.td.webapp.controller.document;

import com.td.model.dto.ModelDTO;
import com.td.model.entity.document.AbstractDocumentModel;
import com.td.model.entity.document.dataset.DocumentDataSet;
import com.td.model.utils.PagingList;
import com.td.service.command.ProducerCommand;
import com.td.service.command.crud.qualifier.FindDocumentDataSet;
import com.td.service.crud.CRUDFacade;
import com.td.service.crud.document.DocumentService;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.PagingResponse;
import com.td.webapp.response.PagingResponseImpl;
import org.springframework.web.bind.annotation.*;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zerotul on 28.01.15.
 */
public abstract class AbstractDocumentController<T extends AbstractDocumentModel, V extends ModelDTO> extends AbstractController{

    private ProducerCommand<Specification<DocumentDataSet>, PagingList<DocumentDataSet>> findDataSet;

    public static class RequestName extends AbstractController.RequestName{
        public static final String FIND_DATA_SET = "find.dataset";
    }

    @RequestMapping(value = "/"+ RequestName.FIND_DATA_SET, method = RequestMethod.POST,
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse findDataSet(@RequestBody Specification<DocumentDataSet> specification){
        PagingList<DocumentDataSet> documents = getFacade().read(specification, findDataSet);
        PagingResponse response = new PagingResponseImpl(documents.getTotalCount());
        response.addResults(documents);
        response.setSuccess(true);
        return response;
    }

    protected abstract CRUDFacade<T, V> getFacade();

    public ProducerCommand<Specification<DocumentDataSet>, PagingList<DocumentDataSet>> getFindDataSet() {
        return findDataSet;
    }

    @Inject
    @FindDocumentDataSet
    public void setFindDataSet(ProducerCommand<Specification<DocumentDataSet>, PagingList<DocumentDataSet>> findDataSet) {
        this.findDataSet = findDataSet;
    }
}
