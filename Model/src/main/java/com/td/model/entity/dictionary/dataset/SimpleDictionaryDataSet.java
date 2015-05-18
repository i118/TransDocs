package com.td.model.entity.dictionary.dataset;

/**
 * Created by zerotul on 08.04.15.
 */
public class SimpleDictionaryDataSet extends DictionaryDataSetImpl {

    private static final long serialVersionUID = -1846661822564851250L;

    private String dictionaryType;

    public String getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType;
    }
}
