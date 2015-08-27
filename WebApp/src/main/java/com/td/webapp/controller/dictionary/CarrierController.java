package com.td.webapp.controller.dictionary;

import com.td.model.repository.dictionary.contractor.ContractorRepository;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.service.context.qualifier.ContractorCrud;
import com.td.service.dto.DriverDTO;
import com.td.service.crud.dictionary.contractor.ContractorCRUDService;
import com.td.webapp.mapper.Filter;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
@Controller
@RequestMapping("/"+CarrierController.CONTROLLER_NAME)
public class CarrierController extends ContractorController<CarrierModel> {

    public static final String CONTROLLER_NAME = "Carrier";

    private ContractorCRUDService<CarrierModel> contractorService;

    public static class RequestName extends ContractorController.RequestName{
        public static final String GET_DRIVERS = "get.drivers";
        public static final String GET_CARS = "get.cars";
    }


    @RequestMapping(value = "/" + RequestName.GET_DRIVERS)
    protected @ResponseBody
    IResponse getDrivers(@RequestParam String filter) {
        Filter jsonFilter = new Filter(filter);
        String contractorId = jsonFilter.findByProperty("carrierId");
        if(contractorId ==null) throw new IllegalArgumentException("carrier filter not found");
        IResponse response = new ResponseImpl();
        getContractorService().getReference(UUID.fromString(contractorId), (CarrierModel carrier) -> {
            carrier.getDrivers().stream().filter((DriverModel driver) -> !driver.isDeleted()).forEach((DriverModel driver) -> {
                response.addResult(new DriverDTO(driver));
            });
        });
        response.setSuccess(true);

        return response;
    }

    @RequestMapping(value = "/" + RequestName.GET_CARS+"/{carId}")
    protected @ResponseBody
    IResponse getCar(@PathVariable UUID carId) {
        IResponse response = new ResponseImpl();
        CarModel carModel = getContractorService().findById(carId, CarModel.TABLE_NAME);
        response.addResult(carModel);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/" + RequestName.GET_CARS)
    protected @ResponseBody
    IResponse getCars(@RequestParam String filter) {
        Filter jsonFilter = new Filter(filter);
        String contractorId = jsonFilter.findByProperty("carrierId");
        if(contractorId ==null) throw new IllegalArgumentException("company filter not found");
        IResponse response = new ResponseImpl();
        getContractorService().getReference(UUID.fromString(contractorId), (CarrierModel carrier) -> {
            carrier.getCars().stream().filter((CarModel carModel) -> !carModel.isDeleted()).forEach((CarModel carModel) -> {
                carModel.getCarBrand();
                carModel.getDrivers().stream().forEach((DriverModel driver)->driver.getFirstName());
                response.addResult(carModel);
            });
        });
        response.setSuccess(true);

        return response;
    }

    @Override
    public ContractorCRUDService<CarrierModel> getContractorService() {
        return contractorService;
    }


    @Inject
    @Override
    @ContractorCrud(ContractorCrud.Type.CARRIER)
    public void setContractorService(ContractorCRUDService<CarrierModel> carrierService) {
        this.contractorService = carrierService;
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }
}
