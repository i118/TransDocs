package com.td.model.entity.dictionary.user;

import com.td.model.validation.ValidationService;
import com.td.model.validation.annotation.NotEmpty;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by konstantinchipunov on 21.02.14.
 */

@Embeddable
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Password implements IPassword {

    private static final long serialVersionUID = 4516747562204649173L;

    @Access(AccessType.FIELD)
    @NotEmpty(message = "{user.password.notnull}")
    @Column(name = Columns.PASSWORD, unique=false, nullable = false)
    private String password;

    public Password(String password, Object salt) {
       this.password = password;
       ValidationService.getInstance().validate(this);
       this.password = PasswordEncoderImpl.getInstance().encodePassword(this.password, salt);
    }

    public Password() {
    }

    public static class Columns{
        public static final String PASSWORD = "password";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Password password1 = (Password) o;

        if (password != null ? !password.equals(password1.password) : password1.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return password != null ? password.hashCode() : 0;
    }

    @Override
    public String toString() {
        return password;
    }

}
