package com.td.model.repository.dictionary.contractor;

import com.td.model.entity.dictionary.company.CustomerModel;

import java.util.function.Supplier;

/**
 * Created by zerotul on 18.03.15.
 */
public class CustomerLiteRowMapper extends AbstractContractorLiteRowMapper<CustomerModel> {
    @Override
    protected Supplier<CustomerModel> getSupplier() {
        return CustomerModel::new;
    }
}
