package com.td.model.repository.dictionary.contractor;

import com.td.model.repository.dictionary.IDataService;
import com.td.model.entity.dictionary.IPerson;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.entity.dictionary.company.CustomerPersonModel;
import com.td.model.entity.dictionary.company.IContractPerson;
import com.td.model.entity.dictionary.company.JuridicalPerson;
import com.td.model.entity.dictionary.company.ICustomerModel;

import java.util.UUID;

import static com.td.model.entity.dictionary.company.LegalAccountDetails.AccountDetailsBuilder;

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
        ICustomerModel customerModel = new CustomerModel();
        customerModel.setFullName("ООО \"Курочка ряба\"");
        customerModel.setShortName("ООО \"Курочка ряба\"");
        customerModel.setLegalAddress(UUID.randomUUID().toString());
        customerModel.setMailingAddress(UUID.randomUUID().toString());
        customerModel.setLegalForm(JuridicalPerson.LegalForm.LLC);
        customerModel.setPhone("1112");
        customerModel.setEmail("test@mail.com");

        AccountDetailsBuilder builder = AccountDetailsBuilder.with()
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

        customerModel.setAccountDetails(builder.build());

        IContractPerson contractPerson =new CustomerPersonModel();
        contractPerson.setFirstName("Дарт");
        contractPerson.setLastName("Вейдер");
        contractPerson.setPhone("11111");
        contractPerson.setEmail("test@mail.com");
        contractPerson.setGender(IPerson.Gender.MAN);

        customerModel.addPerson(contractPerson);

        return new JuridicalPerson[][]{
                {customerModel}
        };
    }
}
