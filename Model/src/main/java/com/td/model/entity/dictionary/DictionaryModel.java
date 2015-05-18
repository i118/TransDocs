package com.td.model.entity.dictionary;

import com.td.model.entity.AbstractTransientModel;

/**
 * Created by konstantinchipunov on 02.01.14.
 */
public class DictionaryModel extends AbstractTransientModel {

    private static final long serialVersionUID = -4137040088883950089L;

    private String description;

    private DictionaryType dictionaryType;

    private String category;

    public DictionaryModel() {
    }

    public DictionaryModel(String description, DictionaryType dictionaryType, String category) {
        this.description = description;
        this.dictionaryType = dictionaryType;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DictionaryType getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(DictionaryType dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
