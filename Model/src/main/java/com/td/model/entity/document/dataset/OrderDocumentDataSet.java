package com.td.model.entity.document.dataset;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by zerotul on 13.03.15.
 */
public interface OrderDocumentDataSet extends DocumentDataSet{

    public String getIncomingNumber();

    public String getOutgoingNumber();

    public String getTransportationType() ;

    public String getCustomerFullName();

    public UUID getCustomerId();

    public String getCarrierFullName();

    public UUID getCarrierId();

    public UUID getManagerId();

    public String getManagerFullName();

    public String getManagerFirstName();

    public String getManagerLastName();

    public String getManagerPatronymic();

    public String getManagerLogin();
}
