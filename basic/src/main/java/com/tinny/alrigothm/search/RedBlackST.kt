package com.tinny.alrigothm.search

/**
 * @author : Tinny
 * @date : Created on 2020/4/1 - 14:51
 * @description :
 * @modified :
 */
class RedBlackST<K : Comparable<K>, V> {
    private var root: Node? = null

    fun size() = size(root)

    private fun size(node: Node?) = when (node) {
        null -> 0
        else -> node.size
    }

    fun get(key: K) = get(root, key)?.value

    private fun get(node: Node?, key: K): Node? {
        if (node == null) return null

        return when {
            key > node.key -> get(node.right, key)
            key < node.key -> get(node.left, key)
            else -> node
        }
    }

    fun min(): K? {
        if (root == null) return null

        var currentNode = root

        while (currentNode!!.left != null) {
            currentNode = currentNode.left
        }
        return currentNode.key
    }

    fun max(): K? {
        if (root == null) return null

        var currentNode = root

        while (currentNode!!.right != null) {
            currentNode = currentNode.right
        }

        return currentNode.key
    }

    fun rank(key: K) = rank(root, key)

    private fun rank(node: Node?, key: K): Int {
        if (node == null) return 0

        return when {
            key > node.key -> size(node.left) + 1 + rank(node.right, key)
            key < node.key -> rank(node.left, key)
            else -> size(node.left)
        }
    }

    fun select(rank: Int) = select(root, rank)

    private fun select(node: Node?, rank: Int): K? {
        if (node == null) return null

        return when {
            rank > size(node.left) -> select(node.right, rank - size(node.left) - 1)
            rank < size(node) -> select(node.left, rank)
            else -> node.key
        }
    }

    fun getLoop(key: K) = getLoop(root, key)?.value

    private fun getLoop(node: Node?, key: K): Node? {
        if (node == null) return null

        var currentNode = node
        while (currentNode != null) {
            currentNode = when {
                key > currentNode.key -> currentNode.right
                key < currentNode.key -> currentNode.left
                else -> return currentNode
            }
        }
        return currentNode
    }

    fun floor(key: K) = floor(root, key)

    private fun floor(node: Node?, key: K): Node? {
        if (node == null) return null

        return when {
            key > node.key -> floor(node.right, key) ?: node
            key < node.key -> floor(node.left, key)
            else -> node
        }
    }

    fun ceiling(key: K) = ceiling(root, key)

    private fun ceiling(node: Node?, key: K): Node? {
        if (node == null) return null

        return when {
            key > node.key -> ceiling(node.right, key)
            key < node.key -> ceiling(node.left, key) ?: node
            else -> node
        }
    }

    fun entries(): Iterator<Pair<K, V>> {
        if (root == null) return mutableListOf<Pair<K, V>>().iterator()

        val mutableListOf = mutableListOf<Pair<K, V>>()
        entries(root, mutableListOf, min()!!, max()!!)
        return mutableListOf.iterator()
    }

    fun entries(from: K, to: K): Iterator<Pair<K, V>> {
        if (root == null) return mutableListOf<Pair<K, V>>().iterator()

        val mutableListOf = mutableListOf<Pair<K, V>>()
        entries(root, mutableListOf, from, to)
        return mutableListOf.iterator()
    }

    private fun entries(node: Node?, mutableList: MutableList<Pair<K, V>>, from: K, to: K) {
        if (node == null) return

        if (node.key < from) return
        if (node.key > to) return
        entries(node.left, mutableList, from, to)
        mutableList.add(Pair(node.key, node.value))
        entries(node.right, mutableList, from, to)
    }

    private fun isRed(node: Node?) = node?.red ?: false

    /**
     * 左旋
     * */
    private fun rotateLeft(node: Node): Node {
        val x = node.right!!
        node.right = x.left
        x.left = node
        x.red = node.red
        x.size = node.size
        node.red = true
        node.size = 1 + size(node.left) + size(node.right)
        return x
    }

    /**
     * 右旋
     * */
    private fun rotateRight(node: Node): Node {
        val x = node.left!!
        node.left = x.right
        x.right = node
        x.red = node.red
        x.size = node.size
        node.red = true
        node.size = 1 + size(node.left) + size(node.right)
        return x
    }

    /**
     * 翻转颜色
     * */
    private fun flipColor(node: Node) {
        node.red = true
        node.left!!.red = false
        node.right!!.red = false
    }

    inner class Node(val key: K, var value: V, var size: Int) {
        var left: Node? = null
        var right: Node? = null
        var red = true
    }
}