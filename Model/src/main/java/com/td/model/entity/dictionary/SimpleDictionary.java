package com.td.model.entity.dictionary;

import com.td.model.entity.AbstractModel;
import com.td.model.validation.annotation.NotEmpty;

import javax.persistence.*;

/**
 * Created by zerotul on 07.04.15.
 */
@Entity
@Table(name =  SimpleDictionary.TABLE_NAME)
public class SimpleDictionary extends AbstractDictionary {
    private static final long serialVersionUID = -6636199517475768924L;

    public static final String TABLE_NAME = "simple_dictionary";

    private String description;

    private DictionaryType dictionaryType;

    public static class Columns{
        public static final String DESCRIPTION = "description";
        public static final String DICTIONARY_TYPE = "dictionary_type";
    }

    @Override
    @NotEmpty(message = "{simpleDict.description.notnull}")
    @Column(name = Columns.DESCRIPTION, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
       this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = Columns.DICTIONARY_TYPE, nullable = false)
    public DictionaryType getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(DictionaryType dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }
}
