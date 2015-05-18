package com.td.model.repository.dictionary.contractor;

import com.td.model.entity.dictionary.company.CarrierModel;

import java.util.function.Supplier;

/**
 * Created by zerotul on 18.03.15.
 */
public class CarrierLiteRowMapper extends AbstractContractorLiteRowMapper<CarrierModel> {
    @Override
    protected Supplier<CarrierModel> getSupplier() {
        return CarrierModel::new;
    }
}
