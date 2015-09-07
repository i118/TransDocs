package com.td.model.dto.mapper.orika;

import com.td.model.dto.dictionary.contractor.DriverDTO;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.TypeFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zerotul.
 */
@Component
public class DriverCustomMapper extends NamedCustomMapper<DriverDTO, DriverModel> {

    public static final String NAME = "driverMapper";

    private Mapper<Object, Object>[] mapper;

    public void mapAtoB(DriverDTO dto, DriverModel destination, MappingContext context){
        if(!dto.isDirtyField("car"))return;

        if(dto.getCar()!=null && !dto.getCar().equals(destination.getCar())){
            CarrierModel carrierModel = destination.getCarrier();
            if(carrierModel==null) throw  new IllegalStateException("carrier in driver can not be null, object id = "+destination.getObjectId());
            CarModel carModel = findCar(dto, carrierModel);
            if(carModel!=null) {
                destination.setCar(carModel);
            }else{
                throw new IllegalStateException("car "+dto.getCar().getObjectId()+ " the carrier "+carrierModel.getObjectId()+" not found");
            }
        }if(dto.getCar()==null){
            destination.setCar(null);
        }
    }

    private CarModel findCar(DriverDTO dto, CarrierModel carrierModel){
        for(CarModel carModel : carrierModel.getCars()){
            if(carModel.getObjectId().equals(dto.getCar().getObjectId())){
                return carModel;
            }
        }
        return null;
    }

    public void setUsedMappers(Mapper<Object, Object>[] mapper) {
        this.mapper = mapper;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
