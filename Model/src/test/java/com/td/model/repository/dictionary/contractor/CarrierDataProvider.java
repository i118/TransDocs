package com.td.model.repository.dictionary.contractor;

import com.td.model.entity.Passport;
import com.td.model.entity.dictionary.Person;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.CarrierPersonModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.model.entity.dictionary.company.ContractPerson;
import com.td.model.entity.dictionary.company.JuridicalPerson;
import com.td.model.entity.dictionary.company.LegalAccountDetails;
import org.testng.annotations.DataProvider;

import java.util.UUID;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
public class CarrierDataProvider {

    public static class DataProviders{
        public static final String CARRIER_DATA = "carrierDataProvider";
    }

    @DataProvider
    public static Object[][] carrierDataProvider(){
        CarrierModel carrierModel = new CarrierModel();
        carrierModel.setFullName("ООО \"Курочка ряба\"");
        carrierModel.setShortName("ООО \"Курочка ряба\"");
        carrierModel.setLegalAddress(UUID.randomUUID().toString());
        carrierModel.setMailingAddress(UUID.randomUUID().toString());
        carrierModel.setLegalForm(JuridicalPerson.LegalForm.LLC);
        carrierModel.setPhone("1112");
        carrierModel.setEmail("test@mail.com");

        LegalAccountDetails details = new LegalAccountDetails();
        details.setAccount("112234543312");
        details.setCorrespondentAccount("112234543312");
        details.setChiefAccountant("Марья Ивановна");
        details.setDirector("Роман Абрамович");
        details.setDirector("Банк первой голактической империи");
        details.setINN("11122233323232");
        details.setKPP("11122233323232");
        details.setBIC("11122233323232");
        details.setOGRN("11122233323232");
        details.setOKVED("11122233323232");

        carrierModel.setAccountDetails(details);

        ContractPerson contractPerson =new CarrierPersonModel();
        contractPerson.setFirstName("Дарт");
        contractPerson.setLastName("Вейдер");
        contractPerson.setPhone("11111");
        contractPerson.setEmail("test@mail.com");
        contractPerson.setGender(Person.Gender.MAN);

        carrierModel.addPerson(contractPerson);
        DriverModel driverModel = new DriverModel();
        driverModel.setFirstName("Иван");
        driverModel.setLastName("Иванов");
        driverModel.setPatronymic("Иваныч");
        driverModel.setGender(Person.Gender.MAN);
        driverModel.setPhone("11111");
        driverModel.setRegistrationAddress("город Иркутск");
        Passport passport = new Passport("2222", "444444", "РОВД");
        driverModel.setPassport(passport);

        CarModel carModel =new CarModel();
        carModel.setCarNumber("111");
        carModel.setCarBrand("volvo");
        carrierModel.addCar(carModel);
        driverModel.setCar(carModel);
        carrierModel.addDriver(driverModel);
        return new CarrierModel[][]{
                {carrierModel}
        };
    }
}
