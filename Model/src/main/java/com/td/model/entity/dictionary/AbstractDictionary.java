package com.td.model.entity.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.listener.DictionaryListener;

import javax.persistence.*;

/**
 * Created by zerotul on 17.03.15.
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners({DictionaryListener.class})
public abstract class AbstractDictionary extends AbstractModel implements Dictionary{

    private static final long serialVersionUID = 3746090511491834441L;

    private UserModel owner;

    public static class Columns extends AbstractModel.Columns{
        public static final String OWNER_ID = "owner_id";
    }

    @Override
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Columns.OWNER_ID, referencedColumnName = UserModel.Columns.OBJECT_ID, nullable = false, updatable = false)
    public UserModel getOwner() {
        return owner;
    }

    @Override
    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}
