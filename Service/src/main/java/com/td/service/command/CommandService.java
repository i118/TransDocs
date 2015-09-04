package com.td.service.command;

import com.td.service.command.argument.Argument;

/**
 * Created by zerotul.
 */
public interface CommandService {

    <T> CommandContext<T> execute(T target, Command<T> command, Argument... args);

    <T, V> ProducerCommandContext<T, V> execute(T target, ProducerCommand<T, V> command, Argument... args);
}
