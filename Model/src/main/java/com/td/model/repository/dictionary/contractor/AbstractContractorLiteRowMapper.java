package com.td.model.repository.dictionary.contractor;


import com.td.model.entity.dictionary.company.Contractor;
import com.td.model.entity.dictionary.company.JuridicalPersonModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Created by zerotul on 18.03.15.
 */
public abstract class AbstractContractorLiteRowMapper<T extends JuridicalPersonModel & Contractor> implements RowMapper<T> {

    @Override
    public  T mapRow(ResultSet rs, int rowNum) throws SQLException{
        T contractor = getSupplier().get();
        contractor.setObjectId(UUID.fromString(rs.getString(JuridicalPersonModel.Columns.OBJECT_ID)));
        contractor.setVersion(rs.getLong(JuridicalPersonModel.Columns.VERSION));
        contractor.setDeleted(rs.getBoolean(JuridicalPersonModel.Columns.IS_DELETED));
        contractor.setFullName(rs.getString(JuridicalPersonModel.Columns.FULL_NAME));
        return contractor;
    }

    protected abstract Supplier<T> getSupplier();
}
