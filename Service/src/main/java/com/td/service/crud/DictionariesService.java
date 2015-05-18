package com.td.service.crud;

import com.td.model.context.qualifier.DictionarySectionQualifier;
import com.td.model.entity.dictionary.DictionaryModel;
import com.td.model.repository.SectionRepository;
import com.td.service.context.qualifier.DictionaryServiceQualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zerotul.
 */
@Service
@DictionaryServiceQualifier
public class DictionariesService implements SectionRepository<DictionaryModel> {


    private final SectionRepository<DictionaryModel> dictionaryRepository;

    @Inject
    public DictionariesService(@DictionarySectionQualifier SectionRepository<DictionaryModel> dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public List<DictionaryModel> findAll() {
        return dictionaryRepository.findAll();
    }

}
