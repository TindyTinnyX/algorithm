package com.tinny.alrigothm.example;

import lombok.Getter;

import java.util.Iterator;

/**
 * @author : Tinny
 * @date : Created on 2020/3/1 - 14:51
 * @description :
 * @modified :
 */
public class ResizingArrayStack<T> implements Iterable<T> {
    private T[] a;
    @Getter
    private int size;

    public ResizingArrayStack() {
        this(1);
    }

    public ResizingArrayStack(int capacity) {
        a = (T[]) new Object[capacity];
    }

    public void push(T item) {
        if (size >= a.length) {
            resize(2 * a.length);
        }
        a[size++] = item;
    }

    public T pop() {
        T item = a[--size];
        a[size] = null;

        if (size > 0 && size <= a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public int capacity() {
        return a.length;
    }

    private void resize(int length) {
        T[] newA = (T[]) new Object[length];
        System.arraycopy(a, 0, newA, 0, size);
        a = newA;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = size;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public T next() {
                return a[--i];
            }
        };
    }
}
