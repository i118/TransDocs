package com.td.model.entity.document.dataset;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import static com.td.model.utils.EntityUtils.buildPersonDescription;

/**
 * Created by zerotul on 13.03.15.
 */
@JsonAutoDetect
@JsonIgnoreProperties({"customerId", "carrierId", "managerId"})
public class OrderDocumentDataSetImpl implements OrderDocumentDataSet {

    private static final long serialVersionUID = -3432843540280944355L;

    private UUID objectId;

    private String incomingNumber;

    private String outgoingNumber;

    private String transportationType;

    private String customerFullName;

    private UUID customerId;

    private String carrierFullName;

    private UUID carrierId;

    private String managerFirstName;

    private UUID managerId;

    private String managerLastName;

    private String managerPatronymic;

    private String managerLogin;

    public OrderDocumentDataSetImpl() {
    }

    public UUID getObjectId() {
        return objectId;
    }

    public String getIncomingNumber() {
        return incomingNumber;
    }

    public String getOutgoingNumber() {
        return outgoingNumber;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public String getCarrierFullName() {
        return carrierFullName;
    }

    public String getManagerFullName() {
        return buildPersonDescription(managerFirstName, managerLastName, managerPatronymic);
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public String getManagerPatronymic() {
        return managerPatronymic;
    }

    public String getManagerLogin() {
        return managerLogin;
    }

    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    public void setIncomingNumber(String incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    public void setOutgoingNumber(String outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public void setCarrierFullName(String carrierFullName) {
        this.carrierFullName = carrierFullName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public void setManagerPatronymic(String managerPatronymic) {
        this.managerPatronymic = managerPatronymic;
    }

    public void setManagerLogin(String managerLogin) {
        this.managerLogin = managerLogin;
    }

    @Override
    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    @Override
    public UUID getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(UUID carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }
}
