package com.tinny.alrigothm.search

import java.time.Duration
import java.time.LocalDateTime
import kotlin.random.Random

/**
 * @author : Tinny
 * @date : Created on 2020/3/15 - 18:58
 * @description :
 * @modified :
 */
object SearchTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val random = Random
        val elements = listOf("runs", "out", "he", "makes", "big", "been", "says", "hello", "to", "cat", "plays", "with", "paper")

        val elementSize = elements.size
        val examples = (1..5000).map {
            elements[random.nextInt(elementSize)]
//            it.toString()
//            random.nextInt(1000).toString()
        }

        var startTime: LocalDateTime

        startTime = LocalDateTime.now()
        val binarySearchST = BinarySearchST<String, Int>()
        examples.forEach {
            when (val value = binarySearchST.get(it)) {
                null -> binarySearchST.put(it, 1)
                else -> binarySearchST.put(it, value + 1)
            }
        }
        println("\nbinarySearch build takes: ${Duration.between(startTime, LocalDateTime.now()).toMillis()}ms")
        binarySearchTest(binarySearchST)

        startTime = LocalDateTime.now()
        val bst = BST<String, Int>()
        examples.forEach {
            when (val value = bst.get(it)) {
                null -> bst.put(it, 1)
                else -> bst.put(it, value + 1)
            }
        }
        println("\nBST build takes: ${Duration.between(startTime, LocalDateTime.now()).toMillis()}ms")
        bstTest(bst)
    }

    private fun binarySearchTest(binarySearchST: BinarySearchST<String, Int>) {
        println("-----------------------")
        binarySearchST.entries().forEach { (key, value) ->
            println("key is: $key, value is: $value, floor is: ${binarySearchST.floor(key)}, ceiling is: ${binarySearchST.ceiling(key)}, rank is: ${binarySearchST.rank(key)}, select is: ${binarySearchST.select(binarySearchST.rank(key))}")
        }

        println()
        println("size is: ${binarySearchST.size()}")
        println("min is: ${binarySearchST.min()}, max is: ${binarySearchST.max()}")
        binarySearchST.deleteMin()
        binarySearchST.deleteMax()
        println("-----------------------")
//        binarySearchST.entries().forEach { (key, value) ->
//            println("key is: $key, value is: $value")
//        }
    }

    private fun bstTest(bst: BST<String, Int>) {
        println("---------------")
        bst.entries().forEach { (key, value) ->
            println("key is: $key, value is: $value, floor is: ${bst.floor(key)}, ceiling is: ${bst.ceiling(key)}, rank is: ${bst.rank(key)}, select is: ${bst.select(bst.rank(key))}")
        }

        println()
        println("size is: ${bst.size()}")
        println("min is: ${bst.min()}, max is: ${bst.max()}")

        bst.select(3).apply {
            if (this != null) {
                bst.delete(this)
            }
        }
        bst.deleteMin()
        bst.deleteMax()

        bst.entries().forEach { (key, value) ->
            println("key is: $key, value is: $value, floor is: ${bst.floor(key)}, ceiling is: ${bst.ceiling(key)}, rank is: ${bst.rank(key)}, select is: ${bst.select(bst.rank(key))}")
        }
    }
}