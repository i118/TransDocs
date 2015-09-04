package com.td.model.dto.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.entity.dictionary.DictionaryType;
import com.td.model.entity.dictionary.SimpleDictionary;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = SimpleDictionary.class)
public class SimpleDictionaryDTO extends DictionaryDTO {
    private static final long serialVersionUID = -8593431833581367324L;
    private String description;

    private DictionaryType dictionaryType;

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
}
