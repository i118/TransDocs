package com.td.model.entity.dictionary;

/**
 * Created by konstantinchipunov on 25.08.14.
 */
public abstract class DictionaryTypes {

    public static final String USER_DICTIONARY = "td_dictionary_user";

    public static final String CUSTOMER_DICTIONARY = "td_dictionary_customer";

    public static final String CARRIER_DICTIONARY = "td_dictionary_carrier";

    public static final String TRANSPORT_TYPE = "transport_type";

    public static final String ORDER_TITLE = "order_title";

    public static final String PAYMENT_DATE  = "payment_date";

    public static class Category{
        public static final String COMPANY = "company";
        public static final String CONTRACTOR = "contractor";
        public static final String ORDER = "order";
    }
}
