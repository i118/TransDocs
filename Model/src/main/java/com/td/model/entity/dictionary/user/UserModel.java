package com.td.model.entity.dictionary.user;


import com.fasterxml.jackson.annotation.*;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.AbstractDictionary;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.listener.ModelModificationListener;
import com.td.model.utils.StringUtil;
import com.td.model.validation.annotation.UniqueLogin;
import com.td.model.validation.annotation.UserValid;
import com.td.model.validation.group.PrePersistGroup;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 06.11.13
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 *
 * Модель пользователя системы
 */
@Entity
@UserValid
@JsonAutoDetect
@JsonTypeName(UserModel.TABLE_NAME)
@Table(name = UserModel.TABLE_NAME)
@UniqueLogin(groups = {PrePersistGroup.class})
@JsonIgnoreProperties(ignoreUnknown = true,value = {"passwordModel"})
@ExcludeSuperclassListeners
@NamedQueries({
        @NamedQuery(name = UserModel.Query.FIND_BY_COMPANY, query = "SELECT u from UserModel u WHERE u.company.objectId = :companyid")
})
@EntityListeners({ModelModificationListener.class})
public class UserModel extends AbstractDictionary implements IUserModel {

    private static final long serialVersionUID = 9065111576799059303L;

    public static final String TABLE_NAME = "td_user";

    private String firstName;

    private String lastName;

    private String patronymic;

    private Gender gender;

    private String login;

    private String mail;

    private String phone;

    private String description;

    @Valid
    private IPassword password;

    private Set<RoleModel> roleModels = new HashSet<RoleModel>();

   private CompanyModel company;

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

    public static class Query{
        public static final String FIND_BY_COMPANY = "user.findByCompany";
    }


    public static class Columns extends AbstractModel.Columns{
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String PATRONYMIC = "patronymic";
        public static final String GENDER = "gender";
        public static final String LOGIN = "login";
        public static final String PHONE = "phone";
        public static final String E_MAIL = "e_mail";
        public static final String COMPANY_OBJECT_ID = "company_object_id";
    }

    public static class Relations extends AbstractModel.Relations{
        public static final String USER_TO_ROLES = "user_to_roles";

        public static final String USER_ID = "user_id";

        public static final String ROLE_ID = "role_id";

        public static final String PASSWORD = "password";
    }

    @Column(name = Columns.FIRST_NAME, unique = false)
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = StringUtil.firstUpperCase(firstName);
    }

    @Column(name = Columns.LAST_NAME, unique = false)
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = StringUtil.firstUpperCase(lastName);
    }

    @Column(name = Columns.PATRONYMIC, unique = false)
    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public void setPatronymic(String patronymic) {
        this.patronymic = StringUtil.firstUpperCase(patronymic);
    }

    @Column(name = Columns.GENDER, unique = false, nullable = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = Columns.LOGIN, unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = Columns.PHONE, unique=false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = Columns.E_MAIL, unique=false)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @ManyToMany(targetEntity = RoleModel.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = Relations.USER_TO_ROLES,
            joinColumns ={ @JoinColumn(name=Relations.USER_ID, nullable=false)},
            inverseJoinColumns = {@JoinColumn(name = Relations.ROLE_ID, nullable = false)}
    )
    public Set<RoleModel> getRoleModels() {
        return roleModels;
    }

    public void setRoleModels(Set<RoleModel> roleModels) {
        this.roleModels = roleModels;
    }

    public void addRoleModel(RoleModel roleModel){
        roleModels.add(roleModel);
    }

    @Override
    @Embedded
    @JsonIgnore
    @Target(Password.class)
    public IPassword getPassword() {
        return password;
    }

    public void setPassword(IPassword password) {
        if(password!=null) {
            this.password = password;
        }
    }

    @ManyToOne
    @JsonBackReference("users")
    @JoinColumn(name = Columns.COMPANY_OBJECT_ID, nullable = false, updatable = false)
    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    @Transient
    public UUID getCompanyId() {
        return getCompany()!=null ? getCompany().getObjectId() : null;
    }

    @Override
    @Transient
    public String getDescription(){
      return getFullName();
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "login='" + login + '\'' +
                '}';
    }
}
