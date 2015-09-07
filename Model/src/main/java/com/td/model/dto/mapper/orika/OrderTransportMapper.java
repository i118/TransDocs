package com.td.model.dto.mapper.orika;

import com.td.model.dto.dictionary.contractor.DriverDTO;
import com.td.model.dto.document.OrderTransportDTO;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;
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
public class OrderTransportMapper extends NamedCustomMapper<OrderTransportDTO, OrderTransport> {

    public static final String NAME = "orderTransportMapper";

    @PersistenceContext(name = "TDPersistenceUnit")
    private EntityManager entityManager;

    public void mapAtoB(OrderTransportDTO dto, OrderTransport destination, MappingContext context){
        mapCar(dto, destination, context);
        mapDriver(dto, destination, context);
    }

    private void mapDriver(OrderTransportDTO dto, OrderTransport destination, MappingContext context) {
        if(!dto.isDirtyField("driver")) return;

        UUID destDriverId = ofNullable(destination).map(OrderTransport::getDriver).map(DriverModel::getObjectId).orElse(null);

        if(dto.getDriver()==null){
            destination.setDriver(null);
        }else {
            if (dto.getDriver() != null && !dto.getDriver().getObjectId().equals(destDriverId)) {
                DriverModel driverModel = entityManager.getReference(DriverModel.class, dto.getDriver().getObjectId());
                destination.setDriver(driverModel);
            }
        }
    }

    private void mapCar(OrderTransportDTO dto, OrderTransport destination, MappingContext context){
        if(!dto.isDirtyField("car")) return;

        UUID destCarId = ofNullable(destination).map(OrderTransport::getCar).map(CarModel::getObjectId).orElse(null);
        if(dto.getCar()==null){
            destination.setCar(null);
        }else {
            if (dto.getCar() != null && !dto.getCar().getObjectId().equals(destCarId)) {
                CarModel carModel = entityManager.getReference(CarModel.class, dto.getCar().getObjectId());
                destination.setCar(carModel);
            }
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
