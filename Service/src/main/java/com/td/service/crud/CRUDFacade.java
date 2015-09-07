package com.td.service.crud;

import com.td.model.dto.DirtySupportDTO;
import com.td.model.entity.Persistent;
import com.td.service.command.ProducerCommand;
import com.td.service.command.argument.Argument;

import java.util.UUID;

/**
 * Created by zerotul.
 */
public interface CRUDFacade<T extends Persistent,V extends DirtySupportDTO> {

    void create(T persistent, Argument... args);

    T update(V dto, Argument... args);

    void delete(T persistent, Argument... args);

    void delete(UUID persistentId, Argument... args);

    void delete(V dto, Argument... args);

    T findById(UUID id);

    <V,D> D read(V target, ProducerCommand<V, D> command, Argument... args);
}
