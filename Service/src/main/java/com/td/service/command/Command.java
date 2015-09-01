package com.td.service.command;

/**
 * Created by zerotul.
 */
public interface Command<T> {

    CommandContext<T> execute(CommandContext<T> context);
}
