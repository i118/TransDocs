package com.td.model.repository.mapper;

import com.td.model.entity.document.dataset.OrderDocumentDataSetImpl;
import com.td.model.entity.usertype.DocumentNumber;
import com.td.model.entity.usertype.converter.DocumentNumberConverter;
import org.zerotul.specification.mapper.AbstractMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zerotul on 13.03.15.
 */
public class OrderSqlDataSetMapper extends AbstractMapper<OrderDocumentDataSetImpl> implements RowMapperAdapter<OrderDocumentDataSetImpl> {

    public OrderSqlDataSetMapper(Class<OrderDocumentDataSetImpl> clazz) {
        super(clazz);
    }

    @Override
    protected void initInternal() {
        super.initInternal();
        addProperty(OrderDocumentDataSetImpl::getIncomingNumber, "incoming_number", OrderDocumentDataSetImpl::setIncomingNumber);
        addProperty(OrderDocumentDataSetImpl::getOutgoingNumber, "outgoing_number", OrderDocumentDataSetImpl::setOutgoingNumber);
        addProperty(OrderDocumentDataSetImpl::getTransportationType, "transportation_type", OrderDocumentDataSetImpl::setTransportationType);
        addProperty(OrderDocumentDataSetImpl::getCustomerFullName, "customer_full_name", OrderDocumentDataSetImpl::setCustomerFullName);
        addProperty(OrderDocumentDataSetImpl::getCarrierFullName, "carrier_full_name",OrderDocumentDataSetImpl::setCarrierFullName);
        addProperty(OrderDocumentDataSetImpl::getManagerFirstName, "m_first_name", OrderDocumentDataSetImpl::setManagerFirstName);
        addProperty(OrderDocumentDataSetImpl::getManagerLastName, "m_last_name", OrderDocumentDataSetImpl::setManagerLastName);
        addProperty(OrderDocumentDataSetImpl::getManagerPatronymic, "m_patronymic", OrderDocumentDataSetImpl::setManagerPatronymic);
        addProperty(OrderDocumentDataSetImpl::getManagerLogin, "m_login", OrderDocumentDataSetImpl::setManagerLogin);
        addProperty(OrderDocumentDataSetImpl::getObjectId, "object_id",OrderDocumentDataSetImpl::setObjectId);
        addProperty(OrderDocumentDataSetImpl::getCustomerId, "customer_id", OrderDocumentDataSetImpl::setCustomerId);
        addProperty(OrderDocumentDataSetImpl::getCarrierId, "carrier_id", OrderDocumentDataSetImpl::setCarrierId);
        addProperty(OrderDocumentDataSetImpl::getManagerId, "manager_id", OrderDocumentDataSetImpl::setManagerId);
    }

    @Override
    protected OrderDocumentDataSetImpl getNewRecord() {
        return new OrderDocumentDataSetImpl();
    }

    @Override
    public OrderDocumentDataSetImpl convertToEntity(ResultSet rs, int rowIndex) {
       OrderDocumentDataSetImpl order = super.convertToEntity(rs, rowIndex);
        DocumentNumberConverter numberConverter = new DocumentNumberConverter();
        try {
            DocumentNumber incomingNumber = numberConverter.convertToEntityAttribute(rs.getString(getPropertyMap("incomingNumber").getPropertyMapName()));
            DocumentNumber outgoingNumber =  numberConverter.convertToEntityAttribute(rs.getString(getPropertyMap("outgoingNumber").getPropertyMapName()));
            order.setIncomingNumber(incomingNumber.toString());
            order.setOutgoingNumber(outgoingNumber.toString());
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getMapName() {
        return "order_document_view";
    }

    @Override
    public OrderDocumentDataSetImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
        return convertToEntity(rs, rowNum);
    }
}
