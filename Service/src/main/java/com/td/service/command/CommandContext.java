package com.td.service.command;

import com.td.service.command.exception.CommandException;

/**
 * Created by zerotul.
 */
public interface CommandContext<T> {

    /**
     * результат выполнения
     * @return
     */
    public Result getResult();

    /**
     * Устанавливает результат выполнения
     * @param result
     */
    public void setResult(Result result);

    /**
     * Объект над которым производитятся операции
     * @return
     */
    public T getTarget();

    /**
     * Возвращает исключение прервавшее выполнение команды
     * @return
     */
    public CommandException getException();

    /**
     * Устанавливает исключение прервавшее выполнение команды
     * @return
     */
    public void setException(CommandException ex);

    default boolean isFailure(){
        return Result.FAILURE.equals(getResult());
    }
}
