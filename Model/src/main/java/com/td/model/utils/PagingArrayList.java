package com.td.model.utils;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.*;

/**
 * Created by zerotul on 17.03.15.
 */
public class PagingArrayList<T> extends UnmodifiableArrayList<T> implements PagingList<T>{

    private final int totalCount;


    /**
     * The given elements are used directly (a defensive copy is not made),
     * and the given size is used as the size of this list. It is the callers
     * responsibility to make sure the size is accurate.
     *
     * @param elements The elements to use.
     * @param size     The size must be <= the length of the elements array
     */
    public PagingArrayList(List<T> elements, int size, int totalCount) {
        super((T[]) elements.toArray(), size);
        this.totalCount = totalCount;
    }

    @Override
    public int getTotalCount() {
        return totalCount;
    }
}
