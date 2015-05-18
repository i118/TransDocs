package com.td.model.entity.usertype.converter;

import org.joda.money.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.td.model.utils.StringUtil.hasText;

/**
 * Created by zerotul on 28.01.15.
 */
@Converter
public class MoneyConverter implements AttributeConverter<Money, String> {

    @Override
    public String convertToDatabaseColumn(Money attribute) {
        return attribute.toString();
    }

    @Override
    public Money convertToEntityAttribute(String dbData) {
        if(!hasText(dbData)) return null;
        return Money.parse(dbData);
    }


}
