package com.td.service.command;

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
    public Exception getException();

    /**
     * Устанавливает исключение прервавшее выполнение команды
     * @return
     */
    public void setException(Exception ex);

    /**
     * Возвращает аргумент необходимый для выполнения команды
     * @param key
     * @return
     */
    public Object getArgument(String key);

    /**
     * Возвращает типизированый аргумент необходимый для выполнения команды
     * @param key
     * @param argumentClass
     * @return
     */
    public <V> V getArgument(String key, Class<V> argumentClass);

    /**
     * Добавляет аргумент необходимый для выполнения команды
     * @param key
     * @param value
     */
    public void addArgument(String key, Object value);

    default boolean isFailure(){
        return Result.FAILURE.equals(getResult());
    }
}
