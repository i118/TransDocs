package com.td.model.context;

import com.td.model.context.qualifier.*;
import com.td.model.repository.GenericJPARepository;
import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.CompanyRepository;
import com.td.model.repository.dictionary.CompanyJPARepository;
import com.td.model.repository.dictionary.DictionaryJPARepository;
import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.repository.dictionary.contractor.CarrierRepository;
import com.td.model.repository.dictionary.contractor.CarrierJPARepository;
import com.td.model.repository.dictionary.contractor.ContractorRepository;
import com.td.model.repository.dictionary.contractor.ContractorJPARepository;
import com.td.model.repository.dictionary.role.RoleRepository;
import com.td.model.repository.dictionary.role.RoleJPARepository;
import com.td.model.repository.dictionary.user.UserRepository;
import com.td.model.repository.dictionary.user.UserJPARepository;
import com.td.model.repository.document.DocumentRepository;
import com.td.model.repository.document.DocumentJPARepository;
import com.td.model.repository.file.FileJPARepository;
import com.td.model.repository.file.FileRepository;
import com.td.model.repository.mapper.*;
import com.td.model.entity.dictionary.SimpleDictionary;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.document.OrderDocumentModel;
import com.td.model.entity.document.dataset.OrderDocumentDataSet;
import com.td.model.entity.document.dataset.OrderDocumentDataSetImpl;
import com.td.model.entity.file.FileModel;
import com.td.model.entity.lock.Lock;
import com.td.model.factory.DataSetSqlMapperFactory;
import com.td.model.factory.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
public class PersistenceRepositoryContext {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean
    public TypeFactory typeFactory(){
        return new TypeFactory();
    }

    @Bean
    public DataSetSqlMapperFactory dataSetSqlMapperFactory(){
        Map<Class, RowMapperAdapter> map = new HashMap<>();
        map.put(OrderDocumentDataSetImpl.class,orderSqlDataSetMapper());
        map.put(OrderDocumentDataSet.class,orderSqlDataSetMapper());
        return new DataSetSqlMapperFactory(map);
    }

    @Bean(initMethod = "init")
    public RowMapperAdapter orderSqlDataSetMapper(){
        return new OrderSqlDataSetMapper(OrderDocumentDataSetImpl.class);
    }

    @Bean
    @UserQualifier
    public UserRepository userModelRepository(){
        UserJPARepository userModelDao = new UserJPARepository();
        userModelDao.setJdbcTemplate(new JdbcTemplate(dataSource));
        userModelDao.setDataSetRowMapper(userDataSetRowMapper());
        return userModelDao;
    }

    @Bean(initMethod = "init")
    public RowMapperAdapter userDataSetRowMapper(){
        return new UserDataSetMapper();
    }

    @Bean
    @RoleQualifier
    public RoleRepository roleModelRepository(){
        RoleJPARepository roleModelDao = new RoleJPARepository();
        roleModelDao.setJdbcTemplate(new JdbcTemplate(dataSource));
        roleModelDao.setDataSetRowMapper(dictionaryDataSetMapper(DictionaryDataSetImpl.class, RoleModel.TABLE_NAME, RoleModel.Columns.DESCRIPTION));
        return roleModelDao;
    }

    @Bean
    @ContractorQualifier(ContractorQualifier.Type.CUSTOMER)
    public ContractorRepository<CustomerModel> customerRepository(){
        ContractorJPARepository<CustomerModel> contractorDao = new ContractorJPARepository<>(CustomerModel.class);
        contractorDao.setJdbcTemplate(new JdbcTemplate(dataSource));
        contractorDao.setDataSetRowMapper(dictionaryDataSetMapper(DictionaryDataSetImpl.class, CustomerModel.TABLE_NAME, CustomerModel.Columns.FULL_NAME));
        return contractorDao;
    }

    @Bean
    @ContractorQualifier(ContractorQualifier.Type.CARRIER)
    public CarrierRepository carrierRepository(){
        CarrierJPARepository contractorDao = new CarrierJPARepository();
        contractorDao.setJdbcTemplate(new JdbcTemplate(dataSource));
        contractorDao.setDataSetRowMapper(dictionaryDataSetMapper(DictionaryDataSetImpl.class, CarrierModel.TABLE_NAME, CarrierModel.Columns.FULL_NAME));
        return contractorDao;
    }

    @Bean
    @FileQualifier
    public FileRepository fileModelRepository(){
        FileJPARepository fileModelDao = new FileJPARepository(FileModel.class);
        return fileModelDao;
    }

    @Bean
    @LockQualifier
    public IRepository<Lock> lockRepository(){
        return new GenericJPARepository<>(Lock.class);
    }

    @Bean
    @DocumentQualifier(DocumentQualifier.Type.ORDER)
    public DocumentRepository<OrderDocumentModel> orderDocumentRepository(){
        DocumentJPARepository<OrderDocumentModel> documentJPADao = new DocumentJPARepository(OrderDocumentModel.class);
        documentJPADao.setJdbcTemplate(new JdbcTemplate(dataSource));
        documentJPADao.setDataSetMapperFactory(dataSetSqlMapperFactory());
        return documentJPADao;
    }

    @Bean
    @CompanyQualifier
    public CompanyRepository companyRepository(){
        CompanyJPARepository companyJPADao = new CompanyJPARepository();
        companyJPADao.setJdbcTemplate(new JdbcTemplate(dataSource));
        companyJPADao.setDataSetRowMapper(dictionaryDataSetMapper(DictionaryDataSetImpl.class, CompanyModel.TABLE_NAME, CompanyModel.Columns.FULL_NAME));
        return companyJPADao;
    }

    @Bean
    @DictionaryQualifier
    public DictionaryRepository<SimpleDictionary> simpleDictionaryRepository(){
        DictionaryJPARepository<SimpleDictionary> dao = new DictionaryJPARepository<>(SimpleDictionary.class);
        dao.setDataSetRowMapper(simpleDictionaryDataSetMapper());
        dao.setJdbcTemplate(new JdbcTemplate(dataSource));
        return dao;
    }

    @Bean(initMethod = "init")
    SimpleDictionaryDataSetMapper simpleDictionaryDataSetMapper(){
        return new SimpleDictionaryDataSetMapper();
    }

    private DictionaryDataSetMapper dictionaryDataSetMapper(Class<? extends DictionaryDataSetImpl> clazz, String tableName, String descriptionProperty){
        DictionaryDataSetMapper mapper = new DictionaryDataSetMapper(clazz, tableName, descriptionProperty);
        mapper.init();
        return mapper;
    }
}
