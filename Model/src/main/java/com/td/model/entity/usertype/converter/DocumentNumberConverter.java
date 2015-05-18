package com.td.model.entity.usertype.converter;

import com.td.model.entity.usertype.DocumentNumber;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.td.model.utils.StringUtil.hasText;

/**
 * Created by zerotul on 26.01.15.
 */
@Converter
public class DocumentNumberConverter implements AttributeConverter<DocumentNumber, String> {

    public static final String SEPARATOR = "#";

    public static final String DATE_PATTERN = "dd.MM.yyyy";

    @Override
    public String convertToDatabaseColumn(DocumentNumber attribute) {
       if(attribute.getNumber() == null) return null;
       StringBuilder documentNumber = new StringBuilder(attribute.getNumber());
        if(attribute.getNumberDate()!=null) {
            documentNumber.append(SEPARATOR)
                    .append(attribute.getNumberDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN))).toString();
        }
        return documentNumber.toString();
    }

    @Override
    public DocumentNumber convertToEntityAttribute(String dbData) {
        if(!hasText(dbData)) return null;
        String[] dataArray = dbData.split(SEPARATOR);
        String number = dataArray[0].trim();
        LocalDate numberDate = null;
        if(dataArray.length>0){
            numberDate = LocalDate.parse(dataArray[1], DateTimeFormatter.ofPattern(DATE_PATTERN));
        }
        return DocumentNumber.DocumentNumberBuilder
                .with(number, numberDate)
                .build();
    }
}
