package com.td.service.command.crud.document;

import com.td.model.entity.document.dataset.DocumentDataSet;
import com.td.model.utils.PagingList;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.FindDocumentDataSet;
import com.td.service.crud.document.DocumentCRUDService;
import com.td.service.crud.document.DocumentService;
import org.springframework.stereotype.Component;
import org.zerotul.specification.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zerotul.
 */
@Component
@FindDocumentDataSet
public class FindDocumentDataSetCommand extends AbstractProducerCommand<Specification<DocumentDataSet>, PagingList<DocumentDataSet>> {

    public static class Arguments{
        public static final String CRUD_SERVICE = "crudService";
    }

    @Override
    protected ProducerCommandContext<Specification<DocumentDataSet>, PagingList<DocumentDataSet>> executeInternal(ProducerCommandContext<Specification<DocumentDataSet>, PagingList<DocumentDataSet>> context, Map<String, Argument> args) throws Exception {
        DocumentService crudService = getArgumentValue(Arguments.CRUD_SERVICE, args);
        context.setProduced(crudService.findDocumentDataSet(context.getTarget()));
        return context;
    }

    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.CRUD_SERVICE);
    }
}
