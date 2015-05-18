package com.td.model.repository.mapper;

import com.td.model.entity.dictionary.dataset.UserDataSet;
import com.td.model.entity.dictionary.user.UserModel;

/**
 * Created by zerotul on 19.03.15.
 */
public class UserDataSetMapper extends DictionaryDataSetMapper<UserDataSet>{


    public UserDataSetMapper() {
        super(UserDataSet.class, UserModel.TABLE_NAME, null);
    }

    @Override
    protected void initInternal() {
        super.initInternal();
        addProperty(UserDataSet::getFirstName, UserModel.Columns.FIRST_NAME, UserDataSet::setFirstName);
        addProperty(UserDataSet::getLastName, UserModel.Columns.LAST_NAME, UserDataSet::setLastName);
        addProperty(UserDataSet::getPatronymic, UserModel.Columns.PATRONYMIC, UserDataSet::setPatronymic);
    }

    @Override
    protected UserDataSet getNewRecord() {
        return new UserDataSet();
    }
}
