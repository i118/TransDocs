package com.td.service.command;

import com.td.service.command.argument.Argument;

/**
 * Created by zerotul.
 */
public interface Command<T> {

    CommandContext<T> execute(CommandContext<T> context, Argument... args);
}
