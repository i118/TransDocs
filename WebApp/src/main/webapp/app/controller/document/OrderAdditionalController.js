/**
 * Created by zerotul on 26.06.15.
 */
Ext.define('TransDocs.controller.document.OrderAdditionalController', {
    extend: 'TransDocs.controller.document.AbstractDocumentController',
    alias: 'controller.orderadditional',



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