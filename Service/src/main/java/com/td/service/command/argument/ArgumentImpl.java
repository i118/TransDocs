package com.td.service.command.argument;

import java.util.Objects;

/**
 * Created by zerotul.
 */
public class ArgumentImpl<T> implements Argument<T> {

    private final String name;

    private final T value;


    private ArgumentImpl(String name, T value) {
        Objects.requireNonNull(name);
        this.name = name;
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class ArgumentBuilder<T>{

        private final String name;

        private T value;

        private ArgumentBuilder(String name) {
            this.name = name;
        }

        public static ArgumentBuilder with(String name){
            Objects.requireNonNull(name);
            return new ArgumentBuilder(name);
        }

        public Argument<T> valueOf(T value) {
            return new ArgumentImpl<>(this.name, value);
        }
    }
}
