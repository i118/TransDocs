package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.entity.Passport;

import javax.persistence.Column;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = Passport.class)
public class PassportDTO extends DirtySupportDTO{

    private static final long serialVersionUID = -761502624598157343L;
    private String serial;

    private String number;

    private String issuedPassport;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssuedPassport() {
        return issuedPassport;
    }

    public void setIssuedPassport(String issuedPassport) {
        this.issuedPassport = issuedPassport;
    }
}
