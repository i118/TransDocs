package com.td.webapp.controller.document;

import com.td.model.context.qualifier.DocumentQualifier;
import com.td.model.dto.document.OrderDocumentDTO;
import com.td.model.entity.document.AbstractDocumentModel;
import com.td.model.entity.document.OrderAdditionalCondition;
import com.td.model.entity.document.OrderDocumentModel;
import com.td.model.entity.document.OrderTransport;
import com.td.service.context.qualifier.OrderCRUDFacade;
import com.td.service.crud.CRUDFacade;
import com.td.service.crud.document.DocumentService;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zerotul on 28.01.15.
 */
@Controller
@RequestMapping("/"+OrderDocumentController.CONTROLLER_NAME)
public class OrderDocumentController extends AbstractDocumentController<OrderDocumentModel, OrderDocumentDTO> {

    public static final String CONTROLLER_NAME = "OrderDocument";

    private DocumentService<OrderDocumentModel> documentService;

    private CRUDFacade<OrderDocumentModel, OrderDocumentDTO> facade;


    public static class RequestName extends AbstractDocumentController.RequestName{
        public static final String GET_TRANSPORT = "get.transport";
        public static final String GET_ADDITIONAL_CONDITION = "get.additionalCondition";
    }

    @RequestMapping(value = "/"+ RequestName.CREATE_OBJECT, method = RequestMethod.POST,
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse createObject(@RequestBody OrderDocumentModel persistent, @RequestParam(required = false) Map<String, String> arguments){
        IResponse response = new ResponseImpl();
        getFacade().create(persistent, obtainArguments(arguments));
        response.setSuccess(true);
        response.addResult(getFacade().findById(persistent.getObjectId()));
        return response;
    }

    @RequestMapping(value = "/"+ RequestName.UPDATE_OBJECT+"/{documentId}", method = RequestMethod.PUT,
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse updateObject(@RequestBody OrderDocumentDTO dto, @RequestParam(required = false) Map<String, String> arguments){
        IResponse response = new ResponseImpl();
        OrderDocumentModel persistent = getFacade().update(dto, obtainArguments(arguments));
        response.setSuccess(true);
        response.addResult(persistent);
        return response;
    }

    @RequestMapping(value = "/"+ RequestName.GET_OBJECT+"/{objectId}", method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse getObject(@PathVariable UUID objectId, @RequestParam Map<String, String> args){
        IResponse<OrderDocumentModel> response = new ResponseImpl();
        OrderDocumentModel order = getFacade().findById(objectId);
        response.addResult(order);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/"+RequestName.GET_TRANSPORT+"/{transportId}", method = {RequestMethod.GET}, headers = CONTENT_TYPE)
    public @ResponseBody IResponse<OrderTransport> getOrderTransport(@PathVariable UUID transportId){
        IResponse<OrderTransport> response = new ResponseImpl();
        OrderTransport orderTransport = getDocumentService().findById(transportId, OrderTransport.TABLE_NAME);
        if(orderTransport!=null){
            response.addResult(orderTransport);
        }
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/"+RequestName.GET_ADDITIONAL_CONDITION+"/{conditionId}", method = {RequestMethod.GET}, headers = CONTENT_TYPE)
    public @ResponseBody IResponse<OrderAdditionalCondition> getAdditionalCondition(@PathVariable UUID conditionId){
        IResponse<OrderAdditionalCondition> response = new ResponseImpl();
        OrderAdditionalCondition orderTransport = getDocumentService().findById(conditionId, OrderAdditionalCondition.TABLE_NAME);
        if(orderTransport!=null){
            response.addResult(orderTransport);
        }
        response.setSuccess(true);
        return response;
    }

    public DocumentService<OrderDocumentModel> getDocumentService() {
        return documentService;
    }

    @Inject
    @DocumentQualifier(DocumentQualifier.Type.ORDER)
    public void setDocumentService(DocumentService<OrderDocumentModel> documentService) {
        this.documentService = documentService;
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }


    @Override
    protected CRUDFacade<OrderDocumentModel, OrderDocumentDTO> getFacade() {
        return facade;
    }

    @Inject
    @OrderCRUDFacade
    public void setFacade(CRUDFacade<OrderDocumentModel, OrderDocumentDTO> facade) {
        this.facade = facade;
    }
}
