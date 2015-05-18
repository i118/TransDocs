package com.td.model.repository.document;

import com.td.model.repository.GenericJPARepository;
import com.td.model.repository.exception.DaoException;
import com.td.model.repository.mapper.RowMapperAdapter;
import com.td.model.factory.DataSetMapperFactory;
import com.td.model.entity.document.AbstractDocumentModel;
import com.td.model.entity.document.dataset.DocumentDataSet;
import com.td.model.utils.PagingArrayList;
import com.td.model.utils.PagingList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.zerotul.specification.Specification;
import org.zerotul.specification.exception.BuildException;
import org.zerotul.specification.expression.Expression;
import org.zerotul.specification.expression.sql.Query;
import org.zerotul.specification.expression.sql.SqlExpressionBuilder;

import java.util.List;


/**
 * Created by zerotul on 26.01.15.
 */
public class DocumentJPARepository<T extends AbstractDocumentModel> extends GenericJPARepository<T> implements DocumentRepository<T> {

    public DocumentJPARepository(Class<T> modelClass) {
        super(modelClass);
    }

    private JdbcTemplate jdbcTemplate;

    private DataSetMapperFactory dataSetMapperFactory;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public <V extends DocumentDataSet> PagingList<V> findDataSet(Specification<? super V> specification) {
        RowMapperAdapter mapper = dataSetMapperFactory.findMapper(specification.getResultClass());
        try {
            Expression<Query> expression = specification.isSatisfied(new SqlExpressionBuilder(mapper));
            Query query =expression.toResult();
            List<V> results = jdbcTemplate.query(query.getQuery(), mapper, query.getParams().toArray());
            int totalCount = jdbcTemplate.queryForObject(query.getRowCountQuery(), Integer.class, query.getParams().toArray());
            PagingList<V> list = new PagingArrayList<>(results, results.size(), totalCount);
            return list;
        } catch (BuildException e) {
            throw new DaoException(e);
        }
    }

    public DataSetMapperFactory getDataSetMapperFactory() {
        return dataSetMapperFactory;
    }

    public void setDataSetMapperFactory(DataSetMapperFactory dataSetMapperFactory) {
        this.dataSetMapperFactory = dataSetMapperFactory;
    }
}
