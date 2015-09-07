package com.td.model.dto.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.entity.usertype.DocumentNumber;

import java.time.LocalDate;

/**
 * Created by zerotul.
 */
@DTO(mappedBy = DocumentNumber.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentNumberDTO extends DirtySupportDTO {

    private String number;

    @JsonSerialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate numberDate;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getNumberDate() {
        return numberDate;
    }

    public void setNumberDate(LocalDate numberDate) {
        this.numberDate = numberDate;
    }
}
