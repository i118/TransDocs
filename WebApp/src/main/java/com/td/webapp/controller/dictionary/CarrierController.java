package com.td.webapp.controller.dictionary;

import com.td.model.dto.dictionary.contractor.CarrierDTO;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.service.command.ProducerCommand;
import com.td.service.command.crud.qualifier.FindCars;
import com.td.service.command.crud.qualifier.FindDrivers;
import com.td.service.context.qualifier.ContractorCRUDFacade;
import com.td.service.crud.CRUDFacade;
import com.td.webapp.mapper.Filter;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
@Controller
@RequestMapping("/"+CarrierController.CONTROLLER_NAME)
public class CarrierController extends ContractorController<CarrierModel, CarrierDTO> {

    public static final String CONTROLLER_NAME = "Carrier";

    private CRUDFacade<CarrierModel, CarrierDTO> facade;

    private ProducerCommand<UUID, List<CarModel>> findCars;

    private ProducerCommand<UUID, List<DriverModel>> findDriver;

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
        response.addResults(getFacade().read(UUID.fromString(contractorId), getFindDriver()));
        response.setSuccess(true);

        return response;
    }

    @RequestMapping(value = "/" + RequestName.GET_CARS + "/{carId}")
    protected
    @ResponseBody
    IResponse getCar(@PathVariable UUID carId) {
        IResponse response = new ResponseImpl();
      //  CarModel carModel = getContractorService().findById(carId, CarModel.TABLE_NAME);
   //     response.addResult(carModel);
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
        response.addResults(getFacade().read(UUID.fromString(contractorId), getFindCars()));
        response.setSuccess(true);

        return response;
    }


    @Override
    public CRUDFacade<CarrierModel, CarrierDTO> getFacade() {
        return facade;
    }

    @Inject
    @ContractorCRUDFacade(ContractorCRUDFacade.Type.CARRIER)
    public void setFacade(CRUDFacade<CarrierModel, CarrierDTO> facade) {
        this.facade = facade;
    }

    public ProducerCommand<UUID, List<CarModel>> getFindCars() {
        return findCars;
    }

    @Inject
    @FindCars
    public void setFindCars(ProducerCommand<UUID, List<CarModel>> findCars) {
        this.findCars = findCars;
    }

    public ProducerCommand<UUID, List<DriverModel>> getFindDriver() {
        return findDriver;
    }

    @Inject
    @FindDrivers
    public void setFindDriver(ProducerCommand<UUID, List<DriverModel>> findDriver) {
        this.findDriver = findDriver;
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }
}
