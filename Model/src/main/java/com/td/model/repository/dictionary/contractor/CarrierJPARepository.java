package com.td.model.repository.dictionary.contractor;

import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;

/**
 * Created by zerotul on 08.04.15.
 */
public class CarrierJPARepository extends ContractorJPARepository<CarrierModel> implements CarrierRepository {

    public CarrierJPARepository() {
        super(CarrierModel.class);
    }


    @Override
    public void saveDriver(DriverModel driver) {
        if (driver.isNew()){
            getEntityManager().persist(driver);
        }else{
            getEntityManager().merge(driver);
        }
    }

    @Override
    public void saveCar(CarModel driver) {
        if (driver.isNew()){
            getEntityManager().persist(driver);
        }else{
            getEntityManager().merge(driver);
        }
    }
}
