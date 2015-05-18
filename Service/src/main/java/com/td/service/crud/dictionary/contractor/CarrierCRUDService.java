package com.td.service.crud.dictionary.contractor;

import com.td.model.context.qualifier.ContractorQualifier;
import com.td.model.context.qualifier.FileQualifier;
import com.td.model.repository.dictionary.contractor.CarrierRepository;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.service.context.qualifier.ContractorCrud;
import com.td.service.crud.file.FileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.td.model.context.qualifier.ContractorQualifier.Type;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
@Service
@ContractorCrud(ContractorCrud.Type.CARRIER)
public class CarrierCRUDService extends ContractorCRUDServiceImpl<CarrierModel, CarrierRepository> {

    private FileService fileService;

    @Inject
    public CarrierCRUDService(@ContractorQualifier(Type.CARRIER) CarrierRepository dao) {
        super(dao);
    }

    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void createDictionaryObject(CarrierModel object, Map<String, String> args) {
        Set<CarModel> savedCars = new HashSet<>();
        if(object.getDrivers()!=null){
            object.getDrivers().stream().filter((DriverModel driver)->driver.isNew()).forEach((DriverModel driver)->{
                if(driver.getCar()!=null && driver.getCar().isNew()){
                    if(driver.getCar().getCarrier()==null)driver.getCar().setCarrier(object);
                    getRepository().saveCar(driver.getCar());
                    savedCars.add(driver.getCar());
                }
                getRepository().saveDriver(driver);
            });
            object.getDrivers().stream().filter((DriverModel driver)->driver.getCar()!=null && driver.getCar().isDeleted()).forEach((DriverModel driver) -> driver.setCar(null));
        }
        if(object.getCars()!=null){
            object.getCars().stream().filter((CarModel car)->car.isNew() && !savedCars.contains(car)).forEach((CarModel car)->{
                if(car.isDeleted()) car.getDrivers().stream().forEach((DriverModel driver)->driver.setCar(null));
                if(car.getCarrier()==null)car.setCarrier(object);
                getRepository().saveCar(car);
            });
        }
        super.createDictionaryObject(object, args);
        getFileService().saveFiles(object);
    }


    @Inject
    @FileQualifier
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public FileService getFileService() {
        return fileService;
    }
}
