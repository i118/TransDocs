package com.td.webapp.controller.document;

import com.td.model.context.qualifier.DocumentQualifier;
import com.td.model.entity.document.OrderDocumentModel;
import com.td.model.entity.document.OrderTransport;
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
public class OrderDocumentController extends AbstractDocumentController<OrderDocumentModel> {

    public static final String CONTROLLER_NAME = "OrderDocument";

    private DocumentService<OrderDocumentModel> documentService;


    public static class RequestName extends AbstractDocumentController.RequestName{
        public static final String GET_TRANSPORT = "get.transport";
    }

    @RequestMapping(value = "/"+ RequestName.CREATE_OBJECT, method = RequestMethod.POST,
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse createObject(@RequestBody OrderDocumentModel persistent, @RequestParam(required = false) Map<String, String> arguments){
        IResponse response = new ResponseImpl();
        getDocumentService().createDocument(persistent);
        response.setSuccess(true);
        response.addResult(getDocumentService().getDocument(persistent.getObjectId()));
        return response;
    }

    @RequestMapping(value = "/"+ RequestName.UPDATE_OBJECT+"/{documentId}", method = RequestMethod.PUT,
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse updateObject(@RequestBody OrderDocumentModel persistent, @RequestParam(required = false) Map<String, String> arguments){
        IResponse response = new ResponseImpl();
        getDocumentService().createDocument(persistent);
        response.setSuccess(true);
        response.addResult(persistent);
        return response;
    }

    @RequestMapping(value = "/"+ RequestName.GET_OBJECT+"/{objectId}", method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse getObject(@PathVariable UUID objectId, @RequestParam Map<String, String> args){
        IResponse<OrderDocumentModel> response = new ResponseImpl();
        OrderDocumentModel order = getDocumentService().getDocument(objectId);
        response.addResult(order);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/"+RequestName.GET_TRANSPORT+"/{transportId}", method = {RequestMethod.GET}, headers = CONTENT_TYPE)
    public @ResponseBody IResponse<OrderTransport> getOrderTransport(@PathVariable UUID transportId){
        IResponse<OrderTransport> response = new ResponseImpl();
        OrderTransport orderTransport = getDocumentService().getModel(transportId, OrderTransport.class);
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
}
