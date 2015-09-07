package com.td.service.command;

import com.td.service.command.argument.Argument;

/**
 * Created by zerotul.
 */
public interface ProducerCommand<T, V> {

    ProducerCommandContext<T, V> execute(ProducerCommandContext<T, V> context, Argument... args);
}
