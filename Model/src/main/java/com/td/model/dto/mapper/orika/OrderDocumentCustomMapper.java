package com.td.model.dto.mapper.orika;

import com.td.model.dto.document.OrderDocumentDTO;
import com.td.model.dto.document.OrderTransportDTO;
import com.td.model.entity.dictionary.company.*;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.entity.document.OrderDocumentModel;
import com.td.model.entity.document.OrderTransport;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

import static java.util.Optional.ofNullable;

/**
 * Created by zerotul.
 */
@Component
public class OrderDocumentCustomMapper extends NamedCustomMapper<OrderDocumentDTO, OrderDocumentModel> {

    public static final String NAME = "OrderDocumentCustomMapper";

    @PersistenceContext(name = "TDPersistenceUnit")
    private EntityManager entityManager;

    public void mapAtoB(OrderDocumentDTO dto, OrderDocumentModel destination, MappingContext context){
        mapManager(dto, destination, context);
        mapCarrier(dto, destination, context);
        mapCustomer(dto, destination, context);
        mapCustomerPerson(dto, destination, context);
        mapCarrierPerson(dto, destination, context);
        mapCompany(dto, destination, context);
    }

    private void mapManager(OrderDocumentDTO dto, OrderDocumentModel destination, MappingContext context){
        if(!dto.isDirtyField("manager")) return;

        if(dto.getManager()==null){
            destination.setManager(null);
        }else {
            UUID destId = ofNullable(destination).map(OrderDocumentModel::getManager).map(IUserModel::getObjectId).orElse(null);
            if (dto.getManager() != null && !dto.getManager().getObjectId().equals(destId)) {
                UserModel model = entityManager.getReference(UserModel.class, dto.getManager().getObjectId());
                destination.setManager(model);
            }
        }
    }

    private void mapCarrier(OrderDocumentDTO dto, OrderDocumentModel destination, MappingContext context){
        if(!dto.isDirtyField("carrier")) return;

        if(dto.getCarrier()==null){
            destination.setCarrier(null);
        }else {
            UUID destId = ofNullable(destination).map(OrderDocumentModel::getCarrier).map(CarrierModel::getObjectId).orElse(null);
            if (dto.getCarrier() != null && !dto.getCarrier().getObjectId().equals(destId)) {
                CarrierModel model = entityManager.getReference(CarrierModel.class, dto.getCarrier().getObjectId());
                destination.setCarrier(model);
            }
        }
    }

    private void mapCustomer(OrderDocumentDTO dto, OrderDocumentModel destination, MappingContext context){
        if(!dto.isDirtyField("customer")) return;

        if(dto.getCustomer()==null){
            destination.setCustomer(null);
        }else {
            UUID destId = ofNullable(destination).map(OrderDocumentModel::getCustomer).map(CustomerModel::getObjectId).orElse(null);
            if (dto.getCustomer() != null && !dto.getCustomer().getObjectId().equals(destId)) {
                CustomerModel model = entityManager.getReference(CustomerModel.class, dto.getCustomer().getObjectId());
                destination.setCustomer(model);
            }
        }
    }

    private void mapCustomerPerson(OrderDocumentDTO dto, OrderDocumentModel destination, MappingContext context){
        if(!dto.isDirtyField("customerPerson")) return;

        if(dto.getCustomerPerson()==null){
            destination.setCustomerPerson(null);
        }else {
            UUID destId = ofNullable(destination).map(OrderDocumentModel::getCustomerPerson).map(CustomerPersonModel::getObjectId).orElse(null);
            if (dto.getCustomerPerson() != null && !dto.getCustomerPerson().getObjectId().equals(destId)) {
                CustomerPersonModel model = entityManager.getReference(CustomerPersonModel.class, dto.getCustomerPerson().getObjectId());
                destination.setCustomerPerson(model);
            }
        }
    }

    private void mapCarrierPerson(OrderDocumentDTO dto, OrderDocumentModel destination, MappingContext context){
        if(!dto.isDirtyField("carrierPerson")) return;

        if(dto.getCarrierPerson()==null){
            destination.setCarrierPerson(null);
        }else {
            UUID destId = ofNullable(destination).map(OrderDocumentModel::getCarrierPerson).map(CarrierPersonModel::getObjectId).orElse(null);
            if (dto.getCarrierPerson() != null && !dto.getCarrierPerson().getObjectId().equals(destId)) {
                CarrierPersonModel model = entityManager.getReference(CarrierPersonModel.class, dto.getCarrierPerson().getObjectId());
                destination.setCarrierPerson(model);
            }
        }
    }

    private void mapCompany(OrderDocumentDTO dto, OrderDocumentModel destination, MappingContext context){
        if(!dto.isDirtyField("company")) return;

        if(dto.getCompany()==null){
            destination.setCompany(null);
        }else {
            UUID destId = ofNullable(destination).map(OrderDocumentModel::getCompany).map(CompanyModel::getObjectId).orElse(null);
            if (dto.getCompany() != null && !dto.getCompany().getObjectId().equals(destId)) {
                CompanyModel model = entityManager.getReference(CompanyModel.class, dto.getCompany().getObjectId());
                destination.setCompany(model);
            }
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
