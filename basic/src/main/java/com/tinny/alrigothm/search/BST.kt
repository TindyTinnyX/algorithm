package com.tinny.alrigothm.search

/**
 * @author : Tinny
 * @date : Created on 2020/3/19 - 23:23
 * @description :
 * @modified :
 */
class BST<K : Comparable<K>, V> {
    private var root: Node? = null

    fun size(): Int = size(root)

    private fun size(node: Node?): Int = when (node) {
        null -> 0
        else -> node.size
    }

    fun get(key: K?): V? = get(root, key)

    fun getLoop(key: K?): V? = getLoop(root, key)

    fun min(): K? {
        if (root == null) return null

        var currentNode = root
        while (currentNode!!.left != null) {
            currentNode = currentNode.left
        }

        return currentNode.key
    }

    private fun min(node: Node?): Node? {
        if (node == null) return null

        return when (node.left) {
            null -> node
            else -> min(node.left)
        }
    }

    fun max(): K? {
        if (root == null) return null

        var currentNode = root
        while (currentNode!!.right != null) {
            currentNode = currentNode.right
        }

        return currentNode.key
    }

    private fun max(node: Node?): Node? {
        if (node == null) return null

        return when (node.right) {
            null -> node
            else -> max(node.right)
        }
    }

    fun floor(key: K): K? = floor(root, key)

    private fun floor(node: Node?, key: K): K? {
        if (node == null) return null

        val compareTo = key.compareTo(node.key)
        return when {
            compareTo > 0 -> floor(node.right, key) ?: node.key
            compareTo < 0 -> floor(node.left, key)
            else -> node.key
        }
    }

    fun ceiling(key: K): K? = ceiling(root, key)

    private fun ceiling(node: Node?, key: K): K? {
        if (node == null) return null;

        val compareTo = key.compareTo(node.key)
        return when {
            compareTo > 0 -> ceiling(node.right, key)
            compareTo < 0 -> ceiling(node.left, key) ?: node.key
            else -> {
                node.key
            }
        }
    }

    fun select(rank: Int): K? = select(root, rank)

    private fun select(node: Node?, rank: Int): K? {
        if (node == null) return null

        val compareTo = rank.compareTo(size(node.left))
        return when {
            compareTo > 0 -> select(node.right, rank - size(node.left) - 1)
            compareTo < 0 -> select(node.left, rank)
            else -> node.key
        }
    }

    fun rank(key: K): Int = rank(root, key)

    private fun rank(node: Node?, key: K): Int {
        if (node == null) return 0

        val compareTo = key.compareTo(node.key)
        return when {
            compareTo > 0 -> size(node.left) + 1 + rank(node.right, key)
            compareTo < 0 -> rank(node.left, key)
            else -> size(node.left)
        }
    }

    private fun get(node: Node?, key: K?): V? {
        if (node == null) return null
        if (key == null) throw IllegalArgumentException("argument can't be null")

        val cmp = key.compareTo(node.key)
        return when {
            cmp > 0 -> get(node.right, key)
            cmp < 0 -> get(node.left, key)
            else -> node.value
        }
    }

    private fun getLoop(node: Node?, key: K?): V? {
        if (node == null) return null
        if (key == null) throw IllegalArgumentException("argument can't be null")

        var currentNode = node

        while (currentNode != null) {
            val cmp = key.compareTo(currentNode.key)
            currentNode = when {
                cmp > 0 -> currentNode.right
                cmp < 0 -> currentNode.left
                else -> return currentNode.value
            }
        }

        return null
    }

    fun put(key: K, value: V) {
        root = put(root, key, value)
    }

    private fun put(node: Node?, key: K, value: V): Node {
        if (node == null) return Node(key, value, 1)

        val cmp = key.compareTo(node.key)
        when {
            cmp > 0 -> node.right = put(node.right, key, value)
            cmp < 0 -> node.left = put(node.left, key, value)
            else -> node.value = value
        }
        node.size = size(node.left) + size(node.right) + 1
        return node
    }

    fun entries(): Iterable<Map.Entry<K, V>> {
        if (root == null) return hashMapOf<K, V>().entries

        val mutableMapOf = mutableMapOf<K, V>()
        entries(root, mutableMapOf, min()!!, max()!!)

        return mutableMapOf.entries
    }

    fun deleteMin() {
        root = deleteMin(root)
    }

    private fun deleteMin(node: Node?): Node? {
        if (node == null) return null

        return when (node.left) {
            null -> node.right
            else -> {
                node.left = deleteMin(node.left)
                node.size = size(node.left) + 1 + size(node.right)
                node
            }
        }
    }

    fun deleteMax() {
        root = deleteMax(root)
    }

    private fun deleteMax(node: Node?): Node? {
        if (node == null) return null

        return when (node.right) {
            null -> node.left
            else -> {
                node.right = deleteMax(node.right)
                node.size = size(node.left) + size(node.right) + 1
                node
            }
        }
    }

    fun delete(key: K) {
        root = delete(root, key)
    }

    private fun delete(node: Node?, key: K): Node? {
        if (node == null) return null

        when {
            key > node.key -> {
                node.right = delete(node.right, key)
                node.size = size(node.left) + size(node.right) + 1
                return node
            }
            key < node.key -> {
                node.left = delete(node.left, key)
                node.size = size(node.left) + size(node.right) + 1
                return node
            }
            else -> return when {
                node.left == null -> node.right
                node.right == null -> node.left
                else -> {
                    val newNode = min(node.right)!!
                    newNode.right = deleteMin(node.right)
                    newNode.left = node.left
                    newNode.size = size(newNode.left) + size(newNode.right) + 1
                    newNode
                }
            }
        }
    }

    private fun entries(node: Node?, mutableMap: MutableMap<K, V>, lo: K, hi: K) {
        if (node == null) return

        val compareToLo = node.key.compareTo(lo)
        if (compareToLo < 0) return
        val compareToHi = node.key.compareTo(hi)
        if (compareToHi > 0) return
        entries(node.left, mutableMap, lo, hi)
        mutableMap[node.key] = node.value
        entries(node.right, mutableMap, lo, hi)
    }

    inner class Node(val key: K, var value: V, var size: Int) {
        var left: Node? = null
        var right: Node? = null
    }
}