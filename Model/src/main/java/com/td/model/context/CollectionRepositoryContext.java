package com.td.model.context;

import com.td.model.context.qualifier.AdminSectionQualifier;
import com.td.model.context.qualifier.DictionarySectionQualifier;
import com.td.model.context.qualifier.JournalQualifier;
import com.td.model.repository.GenericCollectionRepository;
import com.td.model.repository.IRepository;
import com.td.model.entity.AdminSection;
import com.td.model.entity.AdminTypes;
import com.td.model.entity.dictionary.DictionaryModel;
import com.td.model.entity.dictionary.DictionaryType;
import com.td.model.entity.journal.JournalConstants;
import com.td.model.entity.journal.JournalModel;
import com.td.model.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import static com.td.model.entity.dictionary.DictionaryTypes.Category;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
@PropertySource("classpath:/config/model/propeties/ModelDescription.properties")
public class CollectionRepositoryContext {

    @Autowired
    Environment env;

    @Bean
    @DictionarySectionQualifier
    public SectionRepository<DictionaryModel> dictionariesRepository() {
        return new GenericCollectionRepository<>(dictionaryModels());
    }

    private List<DictionaryModel> dictionaryModels(){
        List<DictionaryModel> dictionaryModels = new ArrayList<>();
        dictionaryModels.add(new DictionaryModel(env.getProperty("Dictionary.description.UserDictionary"), DictionaryType.USER_DICTIONARY, Category.COMPANY));
        dictionaryModels.add(new DictionaryModel(env.getProperty("Dictionary.description.CustomerDictionary"), DictionaryType.CUSTOMER_DICTIONARY, Category.CONTRACTOR));
        dictionaryModels.add(new DictionaryModel(env.getProperty("Dictionary.description.CarrierDictionary"), DictionaryType.CARRIER_DICTIONARY, Category.CONTRACTOR));
        dictionaryModels.add(new DictionaryModel(env.getProperty("Dictionary.description.transportType"), DictionaryType.TRANSPORT_TYPE, Category.ORDER));
        dictionaryModels.add(new DictionaryModel(env.getProperty("Dictionary.description.orderTitle"), DictionaryType.ORDER_TITLE, Category.ORDER));
        dictionaryModels.add(new DictionaryModel(env.getProperty("Dictionary.description.paymentDate"), DictionaryType.PAYMENT_DATE, Category.ORDER));
        dictionaryModels.add(new DictionaryModel(env.getProperty("Dictionary.description.borderCrossing"), DictionaryType.BORDER_CROSSING, Category.ORDER));
        dictionaryModels.add(new DictionaryModel(env.getProperty("Dictionary.description.additionalService"), DictionaryType.ADDITIONAL_SERVICE, Category.ORDER));
        return dictionaryModels;
    }

    @Bean
    @JournalQualifier
    public SectionRepository<JournalModel> journalRepository(){
        return new GenericCollectionRepository<>(journalModelList());
    }

    private List<JournalModel> journalModelList(){
        List<JournalModel> journalModelList = new ArrayList<>();
        journalModelList.add(new JournalModel(env.getProperty("Journal.description.OrderJournal"), JournalConstants.ORDER_JOURNAL));
        return journalModelList;
    }

    @Bean
    @AdminSectionQualifier
    public SectionRepository<AdminSection> adminSectionRepository(){
        return new GenericCollectionRepository<>(adminSectionList());
    }

    private List<AdminSection> adminSectionList(){
        List<AdminSection> adminSectionModelList = new ArrayList<>();
        adminSectionModelList.add(new AdminSection(env.getProperty("Admin.description.company"), AdminTypes.COMPANY));
        return adminSectionModelList;
    }

}
