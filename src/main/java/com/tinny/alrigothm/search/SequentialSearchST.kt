package com.tinny.alrigothm.search

/**
 * @author : Tinny
 * @date : Created on 2020/3/15 - 17:57
 * @description :
 * @modified :
 */
class SequentialSearchST<K, V> {
    private var first: Node? = null
    var size = 0
        private set

    fun get(key: K): V? {
        if (key == null) throw IllegalArgumentException("argument can't be null")

        var x = first
        while (x != null) {
            if (key == x.key) {
                return x.value
            }
            x = x.next
        }
        return null
    }

    fun put(key: K, value: V) {
        if (key == null) throw IllegalArgumentException("argument can't be null")

        var x = first
        while (x != null) {
            if (key == x.key) {
                x.value = value
                return
            }
            x = x.next
        }
        first = Node(key, value, first)
        size++
    }

    fun keys(): Iterable<K> {
        val mutableList = mutableListOf<K>()

        var x = first
        while (x != null) {
            mutableList.add(x.key)
            x = x.next
        }

        return mutableList
    }

    fun entries(): Iterable<Map.Entry<K, V>> {
        val hashMap = mutableMapOf<K, V>()

        var x = first
        while (x != null) {
            hashMap.put(x.key, x.value)
            x = x.next
        }

        return hashMap.entries
    }

    fun delete(key: K) {
        if (key == null) throw IllegalArgumentException("argument can't be null")

        if (first == null) {
            return
        }

        var x = first
        var previous = first

        if (first!!.key == key) {
            size--
            first = first!!.next
        }

        while (x != null) {
            if (x.key == key) {
                size--
                previous!!.next = x.next
                return
            }
            previous = x
            x = x.next
        }
    }

    fun contains(key: K): Boolean {
        return get(key) != null
    }

    inner class Node(val key: K, var value: V, var next: Node?)
}

