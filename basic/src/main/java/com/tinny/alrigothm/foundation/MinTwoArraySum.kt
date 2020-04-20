package com.tinny.alrigothm.foundation

import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.min
import kotlin.random.Random

/**
 * @author : Tinny
 * @date : Created on 2020/3/13 - 17:04
 * @description :
 * @modified :
 */
class MaxTwoArraySumAnswer {
    companion object {
        fun getMinPathSumRecursive(lists: List<List<Int>>, cIndex: Int, rIndex: Int): Int {
            if (cIndex == 0) {
                return (0..rIndex).map {
                    lists[0][it]
                }.sum()
            }
            if (rIndex == 0) {
                return (0..cIndex).map {
                    lists[it][0]
                }.sum()
            }

            return min(getMinPathSumRecursive(lists, cIndex - 1, rIndex), getMinPathSumRecursive(lists, cIndex, rIndex - 1)) + lists[cIndex][rIndex]
        }

        fun getMinPathSumIterator(lists: List<List<Int>>, cSize: Int, rSize: Int): Int {
            val copys = Array(cSize) { IntArray(rSize) }

            (0 until cSize).forEach {
                copys[it][0] = (0..it).map { innerIt ->
                    lists[innerIt][0]
                }.sum()
            }
            (0 until rSize).forEach {
                copys[0][it] = (0..it).map { innerIt ->
                    lists[0][innerIt]
                }.sum()
            }

            for (i in 1 until cSize) {
                for (j in 1 until rSize) {
                    copys[i][j] = min(copys[i - 1][j], copys[i][j - 1]) + lists[i][j]
                }
            }

            return copys[cSize - 1][rSize - 1]
        }
    }
}


object MaxTwoArraySum {
    @JvmStatic
    fun main(args: Array<String>) {
        val random = Random
        val rows = /*random.nextInt(1, 30)*/100
        val cols = /*random.nextInt(1, 30)*/100

        val lists = (0 until rows).map {
            (0 until cols).map {
                random.nextInt(10)
            }
        }

        println("n is $rows,\nm is $cols")
        lists.forEach {
            val stringBuilder = StringBuilder()
            val listIterator = it.listIterator()
            while (listIterator.hasNext()) {
                stringBuilder.append("%-3s".format(listIterator.next()))
                if (listIterator.hasNext()) {
                    stringBuilder.append(",")
                }
            }
            println(stringBuilder)
        }


        var start = LocalDateTime.now()
//        println("result is ${MaxTwoArraySumAnswer.getMinPathSumRecursive(lists, rows - 1, cols - 1)}")
//        outPutTime(start)

        start = LocalDateTime.now()
        println("result two is ${MaxTwoArraySumAnswer.getMinPathSumIterator(lists, rows, cols)}")
        outPutTime(start)
    }

    private fun outPutTime(startTime: LocalDateTime) {
        println("time costs: ${Duration.between(startTime, LocalDateTime.now()).toMillis()}")
    }
}