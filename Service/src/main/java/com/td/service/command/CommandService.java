package com.td.service.command;

import java.util.Map;

/**
 * Created by zerotul.
 */
public interface CommandService {

    <T> CommandContext<T> execute(T target, Command<T> command);

    <T> CommandContext<T> execute(T target, Command<T> command, Map<String, Object> arguments);

    <T, V> ProducerCommandContext<T, V> execute(T target, ProducerCommand<T, V> command);

    <T, V> ProducerCommandContext<T, V> execute(T target, ProducerCommand<T, V> command, Map<String, Object> arguments);
}
