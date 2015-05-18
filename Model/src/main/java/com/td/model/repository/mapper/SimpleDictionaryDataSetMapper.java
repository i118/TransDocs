package com.td.model.repository.mapper;

import com.td.model.entity.dictionary.SimpleDictionary;
import com.td.model.entity.dictionary.dataset.SimpleDictionaryDataSet;

/**
 * Created by zerotul on 08.04.15.
 */
public class SimpleDictionaryDataSetMapper extends DictionaryDataSetMapper<SimpleDictionaryDataSet> {

    public SimpleDictionaryDataSetMapper() {
        super(SimpleDictionaryDataSet.class, SimpleDictionary.TABLE_NAME, SimpleDictionary.Columns.DESCRIPTION);
    }

    @Override
    protected void initInternal() {
        super.initInternal();
        addProperty(SimpleDictionaryDataSet::getDictionaryType, SimpleDictionary.Columns.DICTIONARY_TYPE, SimpleDictionaryDataSet::setDictionaryType);
    }

    @Override
    protected SimpleDictionaryDataSet getNewRecord() {
        return new SimpleDictionaryDataSet();
    }
}
