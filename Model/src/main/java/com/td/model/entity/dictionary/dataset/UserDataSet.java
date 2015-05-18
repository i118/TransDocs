package com.td.model.entity.dictionary.dataset;

import static com.td.model.utils.EntityUtils.buildPersonDescription;

/**
 * Created by zerotul on 19.03.15.
 */
public class UserDataSet extends DictionaryDataSetImpl {

    private String firstName;

    private String lastName;

    private String patronymic;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String getDescription() {
        return buildPersonDescription(getFirstName(), getLastName(), getPatronymic());
    }

    @Override
    public void setDescription(String description) {

    }
}
