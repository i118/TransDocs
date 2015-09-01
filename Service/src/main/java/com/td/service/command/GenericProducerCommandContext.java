package com.td.service.command;

/**
 * Created by zerotul.
 */
public class GenericProducerCommandContext<T, V> extends GenericCommandContext<T> implements ProducerCommandContext<T, V>{

    private V produced;

    public GenericProducerCommandContext(T target) {
        super(target);
    }

    @Override
    public V getProduced() {
        return produced;
    }

    @Override
    public void setProduced(V produced) {
        this.produced = produced;
    }
}