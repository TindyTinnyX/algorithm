package com.tinny.alrigothm.example

/**
 * @author : Tinny
 * @date : Created on 2020/3/24 - 0:25
 * @description :
 * @modified :
 */
object KotlinTest {
    @JvmStatic
    fun main(args: Array<String>) {
//        test1()
//        test2()
        test3()
    }

    fun test1() {
        val n = 5;

        val array = arrayOf(
                arrayOf(1, 1, 0, 0, 0),
                arrayOf(1, 1, 1, 0, 0),
                arrayOf(0, 1, 1, 1, 0),
                arrayOf(0, 0, 1, 1, 1),
                arrayOf(0, 0, 0, 1, 1))

        val mutableList = MutableList<MutableSet<Int>>(0) { mutableSetOf() }

        (0 until n).forEach { i ->
            var index = -1
            var create = true
            val tempSet = mutableSetOf<Int>()

            (i until n).forEach { j ->
                if (array[i][j] == 1) {
                    if (!create) {
                        mutableList[index].add(j)
                    } else {
                        for (listInner in 0 until mutableList.size) {
                            if (mutableList[listInner].contains(j)) {
                                create = false
                                index = listInner
                                break
                            }
                        }
                        if (!create) {
                            mutableList[index].add(j)
                        } else {
                            tempSet.add(j)
                        }
                    }
                }
            }

            if (create) {
                mutableList.add(tempSet)
            }
        }

        println("size is: ${mutableList.size}")
    }

    private fun test2() {
        val arrayOf = arrayOf("bell", "time", "me", "timestamp", "bell-ring", "hello", "ring", "ell")

        val reversedMap = arrayOf
                .map {
                    Pair(it.reversed(), it)
                }.sortedBy {
                    it.first
                }

        val stringBuilder = StringBuilder()
        val indexes = mutableListOf<Int>()

        for (i in 0 until reversedMap.size - 1) {
            if (reversedMap[i + 1].first.indexOf(reversedMap[i].first) == -1) {
                stringBuilder.append(reversedMap[i].second).append("#")
            }
        }
        if (!reversedMap.isEmpty()) {
            stringBuilder.append(reversedMap.last().second).append("#")
        }

        val encoding = stringBuilder.toString()

        arrayOf.forEach {
            indexes.add(encoding.indexOf(it))
        }

        println("encoding is: $encoding")
        println("indexes is: $indexes")
    }

    private fun test3() {
        val inputs = arrayOf(1, 2, 3, 3, 5, 5, 2)

        val diffs = IntArray(inputs.size)
        var min = 0
        var total = 0

        for (i in 1 until inputs.size) {
            when {
                inputs[i] > inputs[i - 1] -> diffs[i] = diffs[i - 1] + 1
                else -> {
                    diffs[i] = diffs[i - 1] - 1
                    if (diffs[i] < min) {
                        min = diffs[i]
                    }
                }
            }
            total += diffs[i]
        }

        total += (1 - min) * inputs.size
        println("map is: ${diffs.map {
            it + (1 - min)
        }}")

        println("total is: $total")
    }
}