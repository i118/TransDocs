package com.td.model.entity.dictionary.role;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.AbstractDictionary;
import com.td.model.listener.ModelModificationListener;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 21.11.13
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 * Модель роли
 */
@Entity
@JsonAutoDetect
@JsonTypeName(RoleModel.TABLE_NAME)
@Table(name = RoleModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@ExcludeSuperclassListeners
@EntityListeners({ModelModificationListener.class})
public class RoleModel extends AbstractDictionary implements IRoleModel{

    private static final long serialVersionUID = -7954639361573106116L;

   public static final String TABLE_NAME = "td_role";

   private String roleName;

   private String description;

    public RoleModel() {
    }

    public static class Columns extends AbstractModel.Columns{

        public static final String ROLE_NAME = "role_name";

        public static final String DESCRIPTION = "description";
    }

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

    @Column(name = Columns.ROLE_NAME, nullable = false, unique = true)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    @Column(name = Columns.DESCRIPTION, nullable = true, unique = true)
    public void setDescription(String description) {
        this.description = description;
    }
}
