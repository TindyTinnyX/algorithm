package com.tinny.alrigothm.sort

import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.min
import kotlin.random.Random

/**
 * @author : Tinny
 * @date : Created on 2020/3/7 - 16:00
 * @description :
 * @modified :
 */
fun <T : Comparable<T>> swap(mutableList: MutableList<T>, i: Int, j: Int) {
    val oldValue = mutableList[i]
    mutableList[i] = mutableList[j]
    mutableList[j] = oldValue
}

fun <T : Comparable<T>> selectionSort(list: List<T>): List<T> {
    val mutableList = list.toMutableList()

    val size = mutableList.size
    for (i in 0 until size) {
        var minIndex = i
        for (j in i + 1 until size) {
            if (mutableList[j].compareTo(mutableList[minIndex]) == -1) {
                minIndex = j
            }
        }
        val oldValue = mutableList[i]
        mutableList[i] = mutableList[minIndex]
        mutableList[minIndex] = oldValue
    }
    return mutableList
}

fun <T : Comparable<T>> insertSort(list: List<T>): List<T> {
    val mutableList = list.toMutableList()
    val size = mutableList.size

    for (i in 1 until size) {
        loop@ for (j in i - 1 downTo 0) {
            if (mutableList[j + 1].compareTo(mutableList[j]) == -1) {
                val oldValue = mutableList[j + 1]
                mutableList[j + 1] = mutableList[j]
                mutableList[j] = oldValue
            } else {
                break@loop
            }
        }
    }
    return mutableList
}

fun <T : Comparable<T>> shellSort(list: List<T>): List<T> {
    val mutableList = list.toMutableList()
    val size = mutableList.size

    var h = 1
    while (h < size / 5) {
        h = h * 5 + 1
    }

    while (h >= 1) {
        for (i in h until size) {
            loop@ for (j in i downTo h step h) {
                if (mutableList[j].compareTo(mutableList[j - h]) == -1) {
                    val oldValue = mutableList[j]
                    mutableList[j] = mutableList[j - h]
                    mutableList[j - h] = oldValue
                } else {
                    break@loop
                }
            }
        }
        h /= 5
    }
    return mutableList
}

class MergeSort {
    companion object {
        private lateinit var copyList: MutableList<Any>

        fun <T : Comparable<T>> mergeSortOrder(list: List<T>): List<T> {
            val mutableList = list.toMutableList()
            copyList = list.toMutableList()

            mergeSubSort(mutableList, 0, mutableList.size - 1)
            return mutableList
        }

        fun <T : Comparable<T>> mergeSortBlock(list: List<T>): List<T> {
            val mutableList = list.toMutableList()
            copyList = list.toMutableList()

            var blockSize = 1
            val size = list.size

            while (blockSize < size) {
                for (i in 0..size step blockSize * 2) {
                    mergeSubList(mutableList, i, min(i + 2 * blockSize - 1, size - 1), i + blockSize - 1)
                }
                blockSize *= 2
            }
            return mutableList
        }

        private fun <T : Comparable<T>> mergeSubSort(mutableList: MutableList<T>, lo: Int, hi: Int) {
            if (lo == hi) {
                return
            }
            val mid = (hi + lo) / 2
            mergeSubSort(mutableList, lo, mid)
            mergeSubSort(mutableList, mid + 1, hi)
            mergeSubList(mutableList, lo, hi, mid)
        }

        @Suppress("UNCHECKED_CAST")
        fun <T : Comparable<T>> mergeSubList(mutableList: MutableList<T>, lo: Int, hi: Int, mid: Int): MutableList<T> {
            for (i in lo..hi) {
                copyList[i] = mutableList[i]
            }

            var j = lo
            var k = mid + 1

            for (i in lo..hi) {
                if (j > mid) mutableList[i] = copyList[k++] as T
                else if (k > hi) mutableList[i] = copyList[j++] as T
                else if ((copyList[k] as T).compareTo(copyList[j] as T) == -1) mutableList[i] = copyList[k++] as T
                else mutableList[i] = copyList[j++] as T
            }
            return mutableList
        }
    }
}

class QuickSort {
    companion object {
        private val random = Random

        fun <T : Comparable<T>> basicSort(list: List<T>): List<T> {
            val mutableList = list.toMutableList()

            subSort(mutableList, 0, mutableList.size - 1)
            return mutableList
        }

        fun <T : Comparable<T>> threeBlockSort(list: List<T>): List<T> {
            val mutableList = list.toMutableList()

            threeSubSort(mutableList, 0, mutableList.size - 1)
            return mutableList
        }

        private fun <T : Comparable<T>> subSort(mutableList: MutableList<T>, lo: Int, hi: Int) {
            if (lo >= hi) {
                return
            }

            val ps = partition(mutableList, lo, hi)
            subSort(mutableList, lo, ps - 1)
            subSort(mutableList, ps + 1, hi)
        }

        private fun <T : Comparable<T>> threeSubSort(mutableList: MutableList<T>, lo: Int, hi: Int) {
            if (lo >= hi) {
                return
            }

            val flag = random.nextInt(lo, hi + 1)
            swap(mutableList, flag, lo)

            var lt = lo;
            var i = lo + 1;
            var gt = hi;

            while (i <= gt) {
                when (mutableList[i].compareTo(mutableList[lt])) {
                    -1 -> swap(mutableList, i++, lt++)
                    1 -> swap(mutableList, i, gt--)
                    else -> i++
                }
            }

            threeSubSort(mutableList, lo, lt - 1)
            threeSubSort(mutableList, gt + 1, hi)
        }

        private fun <T : Comparable<T>> partition(mutableList: MutableList<T>, lo: Int, hi: Int): Int {
            val flag = random.nextInt(lo, hi + 1)
            swap(mutableList, flag, lo)

            var i = lo
            var j = hi + 1
            while (true) {
                while (mutableList[++i] <= mutableList[lo]) {
                    if (i >= hi) {
                        break
                    }
                }
                while (mutableList[--j] >= mutableList[lo]) {
                    if (j <= lo) {
                        break
                    }
                }
                if (i >= j) break
                swap(mutableList, i, j)
            }
            swap(mutableList, lo, j)
            return j
        }
    }
}

class MedianFind {
    companion object {
        private val random = Random

        fun <T : Comparable<T>> findMedianQuickSort(mutableList: MutableList<T>, k: Int): T {
            var po: Int
            var lo = 0
            var hi = mutableList.size - 1

            while (lo < hi) {
                po = sortPartition(mutableList, lo, hi)
                when {
                    po == k -> return mutableList[po]
                    po > k -> hi = po - 1
                    po < k -> lo = po + 1
                }
            }

            return mutableList[k]
        }

        fun <T : Comparable<T>> findMedianThreeSub(mutableList: MutableList<T>, k: Int): T {
            var lo = 0
            var hi = mutableList.size - 1

            while (lo < hi) {
                val (lo1, hi1) = threeSortPartition(mutableList, lo, hi)
                when {
                    k in lo1..hi1 -> return mutableList[k]
                    k < lo1 -> hi = lo1 - 1
                    k > hi1 -> lo = hi1 + 1
                }
            }

            return mutableList[k]
        }

        fun <T : Comparable<T>> findMedianPriorityQueue(mutableList: MutableList<T>, k: Int): T {
            val priorityQueue = PriorityQueue<T>()

            var count = 0

            for (i in 0..mutableList.size) {
                priorityQueue.insert(mutableList[i])

                if (priorityQueue.size > 10) {
                    val delMax = priorityQueue.delMax()
                    if (++count == mutableList.size - mutableList.size / 2) {
                        return delMax
                    }
                }
            }

            return priorityQueue.delMax()
        }

        private fun <T : Comparable<T>> sortPartition(mutableList: MutableList<T>, lo: Int, hi: Int): Int {
            val pos = random.nextInt(lo, hi + 1)
            swap(mutableList, lo, pos)

            var i = lo
            var j = hi + 1

            while (true) {
                while (mutableList[++i] <= mutableList[lo]) {
                    if (i >= hi) {
                        break
                    }
                }
                while (mutableList[lo] <= mutableList[--j]) {
                    if (j <= lo) {
                        break
                    }
                }
                if (i >= j) {
                    break
                }
                swap(mutableList, i, j)
            }
            swap(mutableList, lo, j)

            return j
        }

        private fun <T : Comparable<T>> threeSortPartition(mutableList: MutableList<T>, lo: Int, hi: Int): Pair<Int, Int> {
            if (lo >= hi) {
                return Pair(lo, hi)
            }

            val po = random.nextInt(lo, hi + 1)
            swap(mutableList, lo, po)

            var lt = lo
            var i = lo + 1
            var gt = hi

            while (i <= gt) {
                when (mutableList[i].compareTo(mutableList[lt])) {
                    -1 -> swap(mutableList, i++, lt++)
                    1 -> swap(mutableList, i, gt--)
                    0 -> i++
                }
            }

            return Pair(lt, gt)
        }
    }
}

object Main {
    @JvmStatic
    fun main(array: Array<String>) {
        val n = 40000000
        val random = Random
        val exampleValue = IntRange(1, n)
                .map {
                    //                    n - it
                    random.nextInt(100)
                }
//        println("${"%-25s".format("sort before")}: $exampleValue")
        var start = LocalDateTime.now()

//        start = LocalDateTime.now()
//        val selectSortResult = selectionSort(exampleValue)
//        input("selection sort after", start, selectSortResult)
//
//        start = LocalDateTime.now()
//        val insertSortResult = insertSort(exampleValue)
//        input("insert sort after", start, insertSortResult)

//        start = LocalDateTime.now()
//        val defaultSort = exampleValue.sorted()
//        input("default sort after", start, defaultSort)
//        println("median value is: ${defaultSort[defaultSort.size / 2]}")

//        start = LocalDateTime.now()
//        val shellSortResult = shellSort(exampleValue)
//        input("shell sort after", start, shellSortResult)

//        start = LocalDateTime.now()
//        val mergeOrderResult = MergeSort.mergeSortOrder(exampleValue)
//        input("mergeOrder sort after", start, mergeOrderResult)

//        start = LocalDateTime.now()
//        val mergeBlockResult = MergeSort.mergeSortBlock(exampleValue)
//        input("mergeBlock sort after", start, mergeBlockResult)

//        start = LocalDateTime.now()
//        val basicQuickSort = QuickSort.basicSort(exampleValue)
//        input("basicQuick sort after", start, basicQuickSort)
//        verify(defaultSort, basicQuickSort)

//        start = LocalDateTime.now()
//        val threeBlockQuickSort = QuickSort.threeBlockSort(exampleValue)
//        input("threeBlockQuick sort after", start, threeBlockQuickSort)
//        verify(defaultSort, threeBlockQuickSort)

//        start = LocalDateTime.now()
//        val median = MedianFind.findMedianQuickSort(exampleValue.toMutableList(), exampleValue.size / 2)
//        input("quick median time: ", start, emptyList())
//        println("median value is: $median")

        start = LocalDateTime.now()
        val threeMedian = MedianFind.findMedianThreeSub(exampleValue.toMutableList(), exampleValue.size / 2)
        input("three quick median time: ", start, emptyList())
        println("median value is: $threeMedian")

        start = LocalDateTime.now()
        val priorityMedian = MedianFind.findMedianPriorityQueue(exampleValue.toMutableList(), exampleValue.size / 2)
        input("priority median time: ", start, emptyList())
        println("median value is: $threeMedian")
    }
}

fun input(desc: String, startTime: LocalDateTime, sortResult: List<Any>) {
    println("\n${"%-25s".format("$desc takes")}: ${Duration.between(startTime, LocalDateTime.now()).toMillis()}")
//    println("${"%-25s".format(desc)}: $sortResult")
}

fun <T : Comparable<T>> verify(list1: List<T>, list2: List<T>) {
    if (list1.size != list2.size) {
        throw RuntimeException("error!")
    }
    (list1.indices).forEach { i ->
        if (list1[i] != list2[i]) {
            throw RuntimeException("error!")
        }
    }
}