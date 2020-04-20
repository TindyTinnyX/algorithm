package com.tinny.alrigothm.search

/**
 * @author : Tinny
 * @date : Created on 2020/3/15 - 19:39
 * @description :
 * @modified :
 */
class BinarySearchST<K : Comparable<K>, V> {
    private val keys = mutableListOf<K>()
    private val values = mutableListOf<V>()

    fun get(key: K): V? = when (val indexOf = keys.indexOf(key)) {
        -1 -> null
        else -> values[indexOf]
    }

    fun put(key: K, value: V) {
        val rank = rank(key)
        if (rank < keys.size && keys[rank] == key) {
            values[rank] = value
        } else {
            keys.add(rank, key)
            values.add(rank, value)
        }
    }

    fun size(): Int = keys.size

    fun min(): K? = keys.firstOrNull()

    fun max(): K? = keys.lastOrNull()

    fun select(rank: Int): K? = keys.getOrNull(rank)

    fun ceiling(key: K): K? {
        val rank = rank(key)
        return keys.getOrNull(rank);
    }

    fun floor(key: K): K? {
        val rank = rank(key)

        if (rank == keys.size) return keys.lastOrNull()

        return when (key) {
            keys[rank] -> key
            else -> keys.getOrNull(rank - 1)
        }
    }

    fun deleteMin() {
        if (keys.isNotEmpty()) {
            keys.removeAt(0)
            values.removeAt(0)
        }
    }

    fun deleteMax() {
        if (keys.isNotEmpty()) {
            keys.removeAt(keys.size - 1)
            values.removeAt(values.size - 1)
        }
    }

    fun size(lo: K, hi: K): Int {
        if (lo >= hi) {
            return 0
        }
        return when {
            keys.contains(hi) -> rank(hi) - rank(lo) + 1
            else -> rank(hi) - rank(lo)
        }
    }

    fun keys(lo: K, hi: K): Iterable<K> = (rank(lo)..rank(hi))
            .map { index ->
                keys[index]
            }

    fun rank(key: K): Int {
        var lo = 0
        var hi = keys.size - 1

        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            val compare = key.compareTo(keys[mid])
            when {
                compare < 0 -> hi = mid - 1
                compare > 0 -> lo = mid + 1
                compare == 0 -> return mid
            }
        }

        return lo
    }

    fun entries(): Iterable<Map.Entry<K, V>> {
        val map = mutableMapOf<K, V>()
        (keys.indices).forEach {
            map.put(keys[it], values[it])
        }
        return map.entries
    }
}