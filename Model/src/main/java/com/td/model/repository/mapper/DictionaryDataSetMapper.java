package com.td.model.repository.mapper;

import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl;
import org.zerotul.specification.mapper.AbstractMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.td.model.utils.StringUtil.hasText;


/**
 * Created by zerotul on 19.03.15.
 */
public class DictionaryDataSetMapper<T extends DictionaryDataSetImpl> extends AbstractMapper<T> implements RowMapperAdapter<T> {


    private final String mapName;

    private final String descriptionColumnName;


    public DictionaryDataSetMapper(Class<T> clazz, String mapName, String descriptionColumnName) {
        super(clazz);
        this.mapName = mapName;
        this.descriptionColumnName = descriptionColumnName;
    }


    @Override
    protected void initInternal() {
        super.initInternal();
        addProperty(DictionaryDataSetImpl::getObjectId,AbstractModel.Columns.OBJECT_ID, DictionaryDataSetImpl::setObjectId);
        if(hasText(descriptionColumnName)) {
            addProperty(DictionaryDataSetImpl::getDescription, descriptionColumnName, DictionaryDataSetImpl::setDescription);
        }
        addProperty(DictionaryDataSetImpl::isDeleted, AbstractModel.Columns.IS_DELETED, DictionaryDataSetImpl::setDeleted);
        addProperty(DictionaryDataSetImpl::getVersion, AbstractModel.Columns.VERSION, DictionaryDataSetImpl::setVersion);
    }


    @Override
    protected T getNewRecord() {
        return (T) new DictionaryDataSetImpl();
    }

    @Override
    public String getMapName() {
        return mapName;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return convertToEntity(rs, rowNum);
    }
}
