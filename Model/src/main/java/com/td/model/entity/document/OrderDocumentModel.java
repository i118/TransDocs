package com.td.model.entity.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.company.*;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.entity.usertype.DocumentNumber;
import com.td.model.entity.usertype.converter.DocumentNumberConverter;
import com.td.model.entity.usertype.converter.MoneyConverter;
import org.joda.money.Money;

import javax.persistence.*;

/**
 * Created by zerotul on 26.01.15.
 */
@Entity
@Table(name = OrderDocumentModel.TABLE_NAME)
@JsonTypeName(OrderDocumentModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDocumentModel extends AbstractDocumentModel {

    public static final String TABLE_NAME = "order_document";

    private static final long serialVersionUID = -3132942049867971684L;

    private DocumentNumber incomingNumber;

    private DocumentNumber outgoingNumber;

    private TransportationType transportationType;

    private IUserModel manager;

    private CustomerModel customer;

    private CustomerPersonModel customerPerson;

    private CarrierModel carrier;

    private CompanyModel company;

    private Money amountTransaction;

    private String customerPhone;

    private String customerAddress;

    private String customerEmail;

    private PaymentMethod customerPaymentMethod;

    private PaymentMethod carrierPaymentMethod;

    private String carrierEmail;

    private String carrierAddress;

    private String carrierPhone;

    private CarrierPersonModel carrierPerson;

    private String title;

    private String paymentDate;

    public static class Columns extends AbstractModel.Columns{
        public static final String INCOMING_NUMBER = "incoming_number";

        public static final String OUTGOING_NUMBER = "outgoing_number";

        public static final String TRANSPORTATION_TYPE = "transportation_type";

        public static final String MANAGER_OBJECT_ID = "manager_object_id";

        public static final String CUSTOMER_OBJECT_ID = "customer_object_id";

        public static final String CARRIER_OBJECT_ID = "carrier_object_id";

        public static final String COMPANY_OBJECT_ID = "company_object_id";

        public static final String AMOUNT_TRANSACTION = "amount_transaction";

        public static final String CUSTOMER_PAYMENT_METHOD = "customer_payment_method";

        public static final String CUSTOMER_PERSON_ID = "customer_person_id";

        public static final String CUSTOMER_PHONE = "customer_phone";

        public static final String CUSTOMER_ADDRESS = "customer_address";

        public static final String CUSTOMER_EMAIL = "customer_email";

        public static final String CARRIER_PAYMENT_METHOD = "carrier_payment_method";

        public static final String CARRIER_EMAIL = "carrier_email";

        public static final String CARRIER_ADDRESS = "carrier_address";

        public static final String CARRIER_PHONE = "carrier_phone";

        public static final String CARRIER_PERSON_ID = "carrier_person_id";

        public static final String TITLE= "title";

        public static final String PAYMENT_DATE= "payment_date";
    }

    @Column(name = Columns.INCOMING_NUMBER)
    @Convert(converter = DocumentNumberConverter.class)
    public DocumentNumber getIncomingNumber() {
        return incomingNumber;
    }

    public void setIncomingNumber(DocumentNumber incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    @Column(name = Columns.OUTGOING_NUMBER)
    @Convert(converter = DocumentNumberConverter.class)
    public DocumentNumber getOutgoingNumber() {
        return outgoingNumber;
    }

    public void setOutgoingNumber(DocumentNumber outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    @Enumerated(EnumType.STRING)
    @Column(name= Columns.TRANSPORTATION_TYPE)
    public TransportationType getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(TransportationType transportationType) {
        this.transportationType = transportationType;
    }

    @ManyToOne(fetch = FetchType.EAGER,
            targetEntity = UserModel.class
    )
    @JoinColumn(name = Columns.MANAGER_OBJECT_ID)
    public IUserModel getManager() {
        return manager;
    }

    public void setManager(IUserModel manager) {
        this.manager = manager;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Columns.CUSTOMER_OBJECT_ID)
    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Columns.CARRIER_OBJECT_ID)
    public CarrierModel getCarrier() {
        return carrier;
    }

    public void setCarrier(CarrierModel carrier) {
        this.carrier = carrier;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Columns.COMPANY_OBJECT_ID)
    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    @Column(name = Columns.AMOUNT_TRANSACTION)
    @Convert(converter = MoneyConverter.class)
    public Money getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(Money amountTransaction) {
        this.amountTransaction = amountTransaction;
    }

    @Enumerated(EnumType.STRING)
    @Column(name= Columns.CUSTOMER_PAYMENT_METHOD)
    public PaymentMethod getCustomerPaymentMethod() {
        return customerPaymentMethod;
    }

    public void setCustomerPaymentMethod(PaymentMethod customerPaymentMethod) {
        this.customerPaymentMethod = customerPaymentMethod;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Columns.CUSTOMER_PERSON_ID)
    public CustomerPersonModel getCustomerPerson() {
        return customerPerson;
    }

    public void setCustomerPerson(CustomerPersonModel customerPerson) {
        this.customerPerson = customerPerson;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Columns.CARRIER_PERSON_ID)
    public CarrierPersonModel getCarrierPerson() {
        return carrierPerson;
    }

    public void setCarrierPerson(CarrierPersonModel carrierPerson) {
        this.carrierPerson = carrierPerson;
    }

    @Column(name = Columns.CUSTOMER_PHONE)
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Column(name = Columns.CUSTOMER_ADDRESS)
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Column(name = Columns.CUSTOMER_EMAIL)
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Column(name = Columns.CARRIER_PAYMENT_METHOD)
    public PaymentMethod getCarrierPaymentMethod() {
        return carrierPaymentMethod;
    }

    public void setCarrierPaymentMethod(PaymentMethod carrierPaymentMethod) {
        this.carrierPaymentMethod = carrierPaymentMethod;
    }

    @Column(name = Columns.CARRIER_EMAIL)
    public String getCarrierEmail() {
        return carrierEmail;
    }

    public void setCarrierEmail(String carrierEmail) {
        this.carrierEmail = carrierEmail;
    }

    @Column(name = Columns.CARRIER_ADDRESS)
    public String getCarrierAddress() {
        return carrierAddress;
    }

    public void setCarrierAddress(String carrierAddress) {
        this.carrierAddress = carrierAddress;
    }

    @Column(name = Columns.CARRIER_PHONE)
    public String getCarrierPhone() {
        return carrierPhone;
    }

    public void setCarrierPhone(String carrierPhone) {
        this.carrierPhone = carrierPhone;
    }

    @Column(name = Columns.TITLE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = Columns.PAYMENT_DATE)
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }
}
