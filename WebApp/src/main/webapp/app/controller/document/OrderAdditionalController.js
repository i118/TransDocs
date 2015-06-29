/**
 * Created by zerotul on 26.06.15.
 */
Ext.define('TransDocs.controller.document.OrderAdditionalController', {
    extend: 'TransDocs.controller.document.AbstractDocumentController',
    alias: 'controller.orderadditional',


    initViewModel: function(viewModel){
        var document = viewModel.get("document");
        if(!document.isNew()){
            document.getOrderTransport({reload:true});
        }

        if(!document.getOrderTransport()){
            var orderTransport = this.getSession().createRecord("OrderTransport");
            document.setOrderTransport(orderTransport);
        }
        if(!document.getOrderAdditional()){
            var additional = this.getSession().createRecord("OrderAdditional");
            document.setOrderAdditional(additional);
        }
    },

    findTransportType: function(combox, trigger, event){
        var view = this.getView();
        var orderWindow = view.up('window');
        var session = view.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("transport_type", orderWindow,  session, this, combox);
    },

    findBorderCrossing: function(combox, trigger, event){
        var view = this.getView();
        var orderWindow = view.up('window');
        var session = view.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("border_crossing", orderWindow,  session, this, combox);
    },

    findAdditionalService: function(combox, trigger, event){
        var view = this.getView();
        var orderWindow = view.up('window');
        var session = view.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("additional_service", orderWindow,  session, this, combox);
    },
});