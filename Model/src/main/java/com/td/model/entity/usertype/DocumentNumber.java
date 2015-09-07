package com.td.model.entity.usertype;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.td.model.entity.usertype.converter.DocumentNumberConverter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by zerotul on 26.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentNumber implements Serializable {

    private static final long serialVersionUID = 1758907904150664063L;

    private String number;

    @JsonSerialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate numberDate;

    public String getNumber() {
        return number;
    }

    public LocalDate getNumberDate() {
        return numberDate;
    }

    private DocumentNumber() {
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNumberDate(LocalDate numberDate) {
        this.numberDate = numberDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentNumber that = (DocumentNumber) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (numberDate != null ? !numberDate.equals(that.numberDate) : that.numberDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (numberDate != null ? numberDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(number);
        sb.append(" от ").append(numberDate.format(DateTimeFormatter.ofPattern(DocumentNumberConverter.DATE_PATTERN)));
        return sb.toString();
    }

    public static class DocumentNumberBuilder {

        private String number;

        private LocalDate numberDate;

        private DocumentNumberBuilder() {
        }

        public static DocumentNumberBuilder with(String number, LocalDate numberDate) {
            DocumentNumberBuilder builder = new DocumentNumberBuilder();
            builder.number = number;
            builder.numberDate = numberDate;
            return builder;
        }

        public static DocumentNumberBuilder with(){
            return with(null, null);
        }

        public DocumentNumberBuilder setNumber(String number){
            this.number = number;
            return this;
        }

        public DocumentNumberBuilder setNumberDate(LocalDate date){
            this.numberDate = date;
            return this;
        }

        public DocumentNumber build(){
            DocumentNumber documentNumber = new DocumentNumber();
            documentNumber.number = this.number;
            documentNumber.numberDate = this.numberDate;
            return documentNumber;
        }
    }
}
