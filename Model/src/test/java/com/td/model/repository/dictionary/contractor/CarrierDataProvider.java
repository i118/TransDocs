package com.td.model.repository.dictionary.contractor;

import com.td.model.entity.Passport;
import com.td.model.entity.dictionary.IPerson;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.CarrierPersonModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.model.entity.dictionary.company.IContractPerson;
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

        LegalAccountDetails.AccountDetailsBuilder builder = LegalAccountDetails.AccountDetailsBuilder.with()
                .setAccount("112234543312")
                .setCorrespondentAccount("112234543312")
                .setChiefAccountant("Марья Ивановна")
                .setDirector("Роман Абрамович")
                .setDirector("Банк первой голактической империи")
                .setINN("11122233323232")
                .setKPP("11122233323232")
                .setBIC("11122233323232")
                .setOGRN("11122233323232")
                .setOKVED("11122233323232");

        carrierModel.setAccountDetails(builder.build());

        IContractPerson contractPerson =new CarrierPersonModel();
        contractPerson.setFirstName("Дарт");
        contractPerson.setLastName("Вейдер");
        contractPerson.setPhone("11111");
        contractPerson.setEmail("test@mail.com");
        contractPerson.setGender(IPerson.Gender.MAN);

        carrierModel.addPerson(contractPerson);
        DriverModel driverModel = new DriverModel();
        driverModel.setFirstName("Иван");
        driverModel.setLastName("Иванов");
        driverModel.setPatronymic("Иваныч");
        driverModel.setGender(IPerson.Gender.MAN);
        driverModel.setPhone("11111");
        driverModel.setRegistrationAddress("город Иркутск");
        Passport.PassportBuilder passportBuilder = Passport.PassportBuilder.getBuilder("2222","444444","РОВД");
        driverModel.setPassport(passportBuilder.build());

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
