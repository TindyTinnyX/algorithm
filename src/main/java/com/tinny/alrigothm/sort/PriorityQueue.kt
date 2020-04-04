package com.tinny.alrigothm.sort

import kotlin.random.Random

/**
 * @author : Tinny
 * @date : Created on 2020/3/13 - 18:46
 * @description :
 * @modified :
 */
@Suppress("UNCHECKED_CAST")
class PriorityQueue<T : Comparable<T>> {
    var array = Array<Any?>(1) {}
        private set
    var size = 0
        private set

    fun delMax(): T {
        val maxValue = array[1] as T

        array[1] = array[size]
        array[size--] = null
        sink(1)

        if (size > 0 && size <= array.size / 4) {
            resize(array.size / 2)
        }

        return maxValue
    }

    fun insert(element: T) {
        if (size >= array.size - 1) {
            resize(2 * array.size)
        }
        array[++size] = element
        swim(size)
    }

    private fun swim(k: Int) {
        var index = k

        while (index > 1 && (array[index] as T > array[index / 2] as T)) {
            val oldValue = array[index]
            array[index / 2] = array[index]
            array[index] = oldValue
            index /= 2
        }
    }

    private fun sink(k: Int) {
        var index = k

        while (2 * index <= size) {
            var max = 2 * index
            if (max < size && ((array[max] as T) < (array[max + 1] as T))) {
                max += 1
            }
            if (array[index] as T >= array[max] as T) {
                break
            }

            val oldValue = array[index]
            array[index] = array[max]
            array[max] = oldValue
            index = max
        }
    }

    private fun resize(newSize: Int) {
        val copys = Array<Any?>(newSize) {}
        (0..size).forEach {
            copys[it] = array[it]
        }
        array = copys
    }
}

object SomeTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val random = Random

        val priorityQueue = PriorityQueue<Int>()

        repeat((0..20).count()) {
            priorityQueue.insert(random.nextInt(500))
        }

        (0..20).forEach { _ ->
            println("value is ${priorityQueue.delMax()}")
        }
    }
}