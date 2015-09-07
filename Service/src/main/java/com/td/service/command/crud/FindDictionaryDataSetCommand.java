package com.td.service.command.crud;

import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.utils.PagingList;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.FindDictionaryDataSet;
import com.td.service.crud.dictionary.DictionaryCRUDService;
import org.springframework.stereotype.Component;
import org.zerotul.specification.Specification;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by zerotul.
 */
@Component
@FindDictionaryDataSet
public class FindDictionaryDataSetCommand extends AbstractProducerCommand<Specification<DictionaryDataSet>, PagingList<DictionaryDataSet>> {

    public static class Arguments{
        public static final String CRUD_SERVICE = "crudService";
    }

    @Override
    protected ProducerCommandContext<Specification<DictionaryDataSet>, PagingList<DictionaryDataSet>> executeInternal(ProducerCommandContext<Specification<DictionaryDataSet>, PagingList<DictionaryDataSet>> context, Map<String, Argument> args) throws Exception {
        Specification<DictionaryDataSet> spec = context.getTarget();
        DictionaryCRUDService crudService = getArgumentValue(Arguments.CRUD_SERVICE, args);
        PagingList<DictionaryDataSet> dictionaryDataSets = crudService.findDataSet(spec);
        context.setProduced(dictionaryDataSets);
        return context;
    }

    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.CRUD_SERVICE);
    }
}
