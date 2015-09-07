package com.td.model.repository.dictionary.contractor;

import com.td.model.entity.dictionary.company.*;
import com.td.model.repository.dictionary.IDataService;
import com.td.model.entity.dictionary.Person;

import java.util.UUID;

/**
 * Created by konstantinchipunov on 21.08.14.
 */
public class CustomerDataService implements IDataService<JuridicalPerson> {

    private static volatile CustomerDataService customerDataService;

    private CustomerDataService(){}

    public static CustomerDataService getInstance(){
        if(customerDataService==null){
            synchronized (CustomerDataService.class){
                if(customerDataService==null){
                    customerDataService = new CustomerDataService();
                }
            }
        }
       return customerDataService;
    }

    @Override
    public JuridicalPerson[][] getDataArray() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setFullName("ООО \"Курочка ряба\"");
        customerModel.setShortName("ООО \"Курочка ряба\"");
        customerModel.setLegalAddress(UUID.randomUUID().toString());
        customerModel.setMailingAddress(UUID.randomUUID().toString());
        customerModel.setLegalForm(JuridicalPerson.LegalForm.LLC);
        customerModel.setPhone("1112");
        customerModel.setEmail("test@mail.com");

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

        customerModel.setAccountDetails(details);

        ContractPerson contractPerson =new CustomerPersonModel();
        contractPerson.setFirstName("Дарт");
        contractPerson.setLastName("Вейдер");
        contractPerson.setPhone("11111");
        contractPerson.setEmail("test@mail.com");
        contractPerson.setGender(Person.Gender.MAN);

        customerModel.addPerson(contractPerson);

        return new JuridicalPerson[][]{
                {customerModel}
        };
    }
}
