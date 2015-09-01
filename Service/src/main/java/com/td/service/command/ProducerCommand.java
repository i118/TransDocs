package com.td.service.command;

/**
 * Created by zerotul.
 */
public interface ProducerCommand<T, V> {

    ProducerCommandContext<T, V> execute(ProducerCommandContext<T, V> context);
}
