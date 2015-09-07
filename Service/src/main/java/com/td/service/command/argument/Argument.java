package com.td.service.command.argument;

/**
 * Created by zerotul.
 */
public interface Argument<T> extends Comparable<Argument> {

    T getValue();

    String getName();

    default int compareTo(Argument a){
        return this.getName().compareTo(a.getName());
    }
}
