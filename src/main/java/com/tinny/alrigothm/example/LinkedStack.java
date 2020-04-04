package com.tinny.alrigothm.example;

import java.util.Iterator;

/**
 * @author : Tinny
 * @date : Created on 2020/3/2 - 4:56
 * @description :
 * @modified :
 */
public class LinkedStack<T> implements Iterable<T> {
    private Node first;
    private int size;

    public void push(T item) {
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
        size++;
    }

    public T pop() {
        T item = first.item;
        first = first.next;
        size--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node now = first;

            @Override
            public boolean hasNext() {
                return now.next != null;
            }

            @Override
            public T next() {
                now = now.next;
                return now.item;
            }
        };
    }

    private class Node {
        T item;
        Node next;
    }
}
