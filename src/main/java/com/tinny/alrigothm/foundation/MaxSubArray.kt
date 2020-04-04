package com.tinny.alrigothm.foundation

/**
 * @author : Tinny
 * @date : Created on 2020/3/2 - 23:07
 * @description :
 * @modified :
 */
fun findMaxCrossingSubArray(array: Array<Int>, low: Int, high: Int): Triple<Int, Int, Int> {
    val mid = (high + low) / 2

    var leftMax = Int.MIN_VALUE
    var left = mid
    var sum = 0
    for (i in mid downTo low) {
        sum += array[i]
        if (sum > leftMax) {
            leftMax = sum
            left = i
        }
    }

    var rightMax = Int.MIN_VALUE
    var right = mid + 1
    sum = 0
    for (i in mid + 1..high) {
        sum += array[i]
        if (sum > rightMax) {
            rightMax = sum
            right = i
        }
    }

    return Triple(left, right, leftMax + rightMax)
}

fun findMaxSubArray(array: Array<Int>, low: Int, high: Int): Triple<Int, Int, Int> {
    if (low == high) {
        return Triple(low, high, array[low])
    } else {
        val mid = (high + low) / 2
        val (low_left, low_right, low_sum) = findMaxSubArray(array, low, mid)
        val (high_left, high_right, high_sum) = findMaxSubArray(array, mid + 1, high)
        val (cross_left, cross_right, cross_sum) = findMaxCrossingSubArray(array, low, high)
        return when (maxOf(low_sum, high_sum, cross_sum)) {
            low_sum -> Triple(low_left, low_right, low_sum)
            high_sum -> Triple(high_left, high_right, high_sum)
            cross_sum -> Triple(cross_left, cross_right, cross_sum)
            else -> throw IllegalArgumentException("unknown exception!")
        }
    }
}

fun findMaxSubArray2(array: Array<Int>): Triple<Int, Int, Int> {
    var max = Int.MIN_VALUE
    var counter = Int.MIN_VALUE
    var left = 0
    var right = 0
    var tempLeft = 0

    array.forEachIndexed { index, i ->
        if (counter + i > i) {
            counter += i
        } else {
            counter = i;
            tempLeft = index;
        }

        if (counter > max) {
            max = counter;
            left = tempLeft;
            right = index;
        }
        println("max: $max, counter: $counter, left: $left, right: $right, tempLeft: $tempLeft")
    }

    return Triple(left, right, max)
}

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = arrayOf(13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7)

        val (left, right, maxSum) = findMaxSubArray(array, 0, array.size - 1)
        println("left: $left, right: $right, maxSum: $maxSum")

        val (left2, right2, maxSum2) = findMaxSubArray2(array)
        println("left: $left2, right: $right2, maxSum: $maxSum2")
    }
}