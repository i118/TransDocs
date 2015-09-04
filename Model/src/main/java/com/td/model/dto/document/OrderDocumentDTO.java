package com.td.model.dto.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.dto.ModelDTO;
import com.td.model.dto.dictionary.contractor.*;
import com.td.model.dto.dictionary.user.UserDTO;
import com.td.model.entity.dictionary.company.*;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.document.*;
import com.td.model.entity.usertype.DocumentNumber;
import org.joda.money.Money;

/**
 * Created by zerotul.
 */
@DTO(mappedBy = OrderDocumentModel.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDocumentDTO extends ModelDTO {

    private static final long serialVersionUID = -5654769686037857025L;

    private DocumentNumberDTO incomingNumber;

    private DocumentNumberDTO outgoingNumber;

    private TransportationType transportationType;

    private UserDTO manager;

    private CustomerDTO customer;

    private CustomerPersonDTO customerPerson;

    private CarrierDTO carrier;

    private CompanyDTO company;

    private Money amountTransaction;

    private String customerPhone;

    private String customerAddress;

    private String customerEmail;

    private PaymentMethod customerPaymentMethod;

    private PaymentMethod carrierPaymentMethod;

    private String carrierEmail;

    private String carrierAddress;

    private String carrierPhone;

    private CarrierPersonDTO carrierPerson;

    private String title;

    private String paymentDate;

    private OrderAdditionalDTO orderAdditional;

    private OrderTransportDTO orderTransport;

    private OrderAdditionalConditionDTO customerAdditionalCondition;
    private OrderAdditionalConditionDTO carrierAdditionalCondition;


    public DocumentNumberDTO getIncomingNumber() {
        return incomingNumber;
    }

    public void setIncomingNumber(DocumentNumberDTO incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    public DocumentNumberDTO getOutgoingNumber() {
        return outgoingNumber;
    }

    public void setOutgoingNumber(DocumentNumberDTO outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public TransportationType getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(TransportationType transportationType) {
        this.transportationType = transportationType;
    }

    public UserDTO getManager() {
        return manager;
    }

    public void setManager(UserDTO manager) {
        this.manager = manager;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public CustomerPersonDTO getCustomerPerson() {
        return customerPerson;
    }

    public void setCustomerPerson(CustomerPersonDTO customerPerson) {
        this.customerPerson = customerPerson;
    }

    public CarrierDTO getCarrier() {
        return carrier;
    }

    public void setCarrier(CarrierDTO carrier) {
        this.carrier = carrier;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public Money getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(Money amountTransaction) {
        this.amountTransaction = amountTransaction;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public PaymentMethod getCustomerPaymentMethod() {
        return customerPaymentMethod;
    }

    public void setCustomerPaymentMethod(PaymentMethod customerPaymentMethod) {
        this.customerPaymentMethod = customerPaymentMethod;
    }

    public PaymentMethod getCarrierPaymentMethod() {
        return carrierPaymentMethod;
    }

    public void setCarrierPaymentMethod(PaymentMethod carrierPaymentMethod) {
        this.carrierPaymentMethod = carrierPaymentMethod;
    }

    public String getCarrierEmail() {
        return carrierEmail;
    }

    public void setCarrierEmail(String carrierEmail) {
        this.carrierEmail = carrierEmail;
    }

    public String getCarrierAddress() {
        return carrierAddress;
    }

    public void setCarrierAddress(String carrierAddress) {
        this.carrierAddress = carrierAddress;
    }

    public String getCarrierPhone() {
        return carrierPhone;
    }

    public void setCarrierPhone(String carrierPhone) {
        this.carrierPhone = carrierPhone;
    }

    public CarrierPersonDTO getCarrierPerson() {
        return carrierPerson;
    }

    public void setCarrierPerson(CarrierPersonDTO carrierPerson) {
        this.carrierPerson = carrierPerson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public OrderAdditionalDTO getOrderAdditional() {
        return orderAdditional;
    }

    public void setOrderAdditional(OrderAdditionalDTO orderAdditional) {
        this.orderAdditional = orderAdditional;
    }

    public OrderTransportDTO getOrderTransport() {
        return orderTransport;
    }

    public void setOrderTransport(OrderTransportDTO orderTransport) {
        this.orderTransport = orderTransport;
    }

    public OrderAdditionalConditionDTO getCustomerAdditionalCondition() {
        return customerAdditionalCondition;
    }

    public void setCustomerAdditionalCondition(OrderAdditionalConditionDTO customerAdditionalCondition) {
        this.customerAdditionalCondition = customerAdditionalCondition;
    }

    public OrderAdditionalConditionDTO getCarrierAdditionalCondition() {
        return carrierAdditionalCondition;
    }

    public void setCarrierAdditionalCondition(OrderAdditionalConditionDTO carrierAdditionalCondition) {
        this.carrierAdditionalCondition = carrierAdditionalCondition;
    }
}
