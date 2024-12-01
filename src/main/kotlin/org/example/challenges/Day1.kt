package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File
import kotlin.math.absoluteValue

object Day1 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        var total = 0
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()
        file.forEachLine {
            val (first, second) = it.split("  ").map { string -> string.trimStart().trimEnd().toInt() }
            firstList.add(first)
            secondList.add(second)
        }
        firstList.sortDescending()
        secondList.sortDescending()
        for (i in firstList.indices) {
            total += (firstList[i] - secondList[i]).absoluteValue
        }
        return total
    }

    override fun part2(file: File): Int {
        var total = 0
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()
        file.forEachLine {
            val (first, second) = it.split("  ").map { string -> string.trimStart().trimEnd().toInt() }
            firstList.add(first)
            secondList.add(second)
        }
        for (i in firstList.indices) {
            total += firstList[i] * secondList.count { value -> value == firstList[i] }
        }
        return total
    }

}
