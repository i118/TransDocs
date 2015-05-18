package com.td.model.repository.dictionary.contractor;

import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;

/**
 * Created by zerotul on 08.04.15.
 */
public interface CarrierRepository extends ContractorRepository<CarrierModel> {

    public void saveDriver(DriverModel driver);

    public void saveCar(CarModel driver);
}
