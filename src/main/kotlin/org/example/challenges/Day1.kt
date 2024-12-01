package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File
import kotlin.math.absoluteValue

object Day1 : TwoPartChallenge<Int, Int> {

    private val numbers: List<Pair<String, Int>> = listOf(
        Pair("zero", 0),
        Pair("one", 1),
        Pair("two", 2),
        Pair("three", 3),
        Pair("four", 4),
        Pair("five", 5),
        Pair("six", 6),
        Pair("seven", 7),
        Pair("eight", 8),
        Pair("nine", 9),
    )

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
