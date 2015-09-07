package com.td.service.command;

/**
 * Created by zerotul.
 */
public interface ProducerCommandContext<T, V> extends CommandContext<T>  {

    /**
     * Возвращает произведенный коммандой объект
     * @return
     */
    public V getProduced();

    /**
     * Устанавливает произведенный коммандой объект
     * @return
     */
    public void setProduced(V produced);
}
