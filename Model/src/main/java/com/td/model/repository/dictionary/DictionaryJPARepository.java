package com.td.model.repository.dictionary;

import com.td.model.repository.GenericJPARepository;
import com.td.model.repository.exception.DaoException;
import com.td.model.repository.mapper.RowMapperAdapter;
import com.td.model.entity.dictionary.Dictionary;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
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
 * Created by zerotul on 19.03.15.
 */
public class DictionaryJPARepository<T extends Dictionary> extends GenericJPARepository<T> implements DictionaryRepository<T> {

    private JdbcTemplate jdbcTemplate;

    private RowMapperAdapter dataSetRowMapper;

    public DictionaryJPARepository(Class<T> modelClass) {
        super(modelClass);
    }

    @Override
    public <V extends DictionaryDataSet> PagingList<V> findDataSet(Specification<? super V> specification) {
        try {
            Expression<Query> expression = specification.isSatisfied(new SqlExpressionBuilder(getDataSetRowMapper()));
            Query query = expression.toResult();
            List<V> results = getJdbcTemplate().query(query.getQuery(), getDataSetRowMapper(), query.getParams().toArray());
            int rowCount = getJdbcTemplate().queryForObject(query.getRowCountQuery(), Integer.class, query.getParams().toArray());
            PagingList<V> pagingList = new PagingArrayList<>(results, results.size(), rowCount);
            return pagingList;
        } catch (BuildException e) {
            throw new DaoException(e);
        }
    }

    public void testJpql(){
       javax.persistence.Query query = getEntityManager().createQuery("SELECT e FROM CarrierModel e");
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapperAdapter getDataSetRowMapper() {
        return dataSetRowMapper;
    }

    public void setDataSetRowMapper(RowMapperAdapter dataSetRowMapper) {
        this.dataSetRowMapper = dataSetRowMapper;
    }
}
