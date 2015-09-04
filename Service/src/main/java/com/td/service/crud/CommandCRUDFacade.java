package com.td.service.crud;

import com.td.model.dto.DirtySupportDTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.Persistent;
import com.td.service.command.*;
import com.td.service.command.CommandArguments.CRUD;
import com.td.service.command.argument.Argument;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by zerotul.
 */
public class CommandCRUDFacade<T extends Persistent, V extends ModelDTO> implements CRUDFacade<T, V> {

    private final CRUDService<T> crudService;

    private final CommandService commandService;

    private ProducerCommand<T, T> createCommand;

    private ProducerCommand<V, T> updateCommand;

    private Command<T> deleteCommand;

    public CommandCRUDFacade(CRUDService<T> crudService, CommandService commandService) {
        this.crudService = crudService;
        this.commandService = commandService;
    }

    @Override
    @PreAuthorize(GenericCRUDService.PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void create(T persistent, Argument... args) {
        CommandContext<T> context = commandService.execute(persistent, createCommand, getDefaultArguments(args));
        if (context.isFailure()) throw context.getException();
    }

    @Override
    @PreAuthorize(GenericCRUDService.PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public T update(V dto, Argument... args) {
        ProducerCommandContext<V, T> context = commandService.execute(dto, updateCommand, getDefaultArguments(args));
        if (context.isFailure()) throw context.getException();
        return context.getProduced();
    }

    @Override
    @PreAuthorize(GenericCRUDService.PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void delete(T persistent, final Argument... args) {
        CommandContext<T> context = commandService.execute(persistent, deleteCommand, getDefaultArguments(args));
        if (context.isFailure()) throw context.getException();
    }

    @Override
    @PreAuthorize(GenericCRUDService.PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void delete(UUID persistentId, Argument... args) {
        T persistent = crudService.getReference(persistentId);
        delete(persistent, args);
    }

    @Override
    @PreAuthorize(GenericCRUDService.PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void delete(V dto, Argument... args) {
        T persistent = crudService.getReference(dto.getObjectId());
        delete(persistent);
    }

    @Override
    @PreAuthorize(GenericCRUDService.PRE_AUTHORIZE)
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public T findById(UUID id) {
        return crudService.findById(id);
    }

    @Override
    @PreAuthorize(GenericCRUDService.PRE_AUTHORIZE)
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public <V, D> D read(V target, ProducerCommand<V, D> command, Argument... args) {
        ProducerCommandContext<V, D> context = commandService.execute(target, command, getDefaultArguments(args));
        if (context.isFailure()) throw context.getException();
        return context.getProduced();
    }

    private Argument[] getDefaultArguments(final Argument... args){
       return new ArrayList<Argument>() {
            {
                addAll(Arrays.asList(args));
                add(CRUD.CRUD_SERVICE.valueOf(crudService));
            }
        }.toArray(new Argument[0]);
    }

    public ProducerCommand<T, T> getSaveCommand() {
        return createCommand;
    }

    public void setCreateCommand(ProducerCommand<T, T> saveCommand) {
        this.createCommand = saveCommand;
    }

    public ProducerCommand<V, T> getUpdateCommand() {
        return updateCommand;
    }

    public void setUpdateCommand(ProducerCommand<V, T> updateCommand) {
        this.updateCommand = updateCommand;
    }

    public Command<T> getDeleteCommand() {
        return deleteCommand;
    }

    public void setDeleteCommand(Command<T> deleteCommand) {
        this.deleteCommand = deleteCommand;
    }
}
