package com.td.service.crud;

/**
 * Created by konstantinchipunov on 25.07.14.
 */
@FunctionalInterface
public interface LazyInitVisiter<T> {

    public void initLazy(T persistent);
}
