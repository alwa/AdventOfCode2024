package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

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
        file.forEachLine {
            it.find
            val firstDigit = it.find { char -> char.isDigit() }
            val lastDigit = it.findLast { char -> char.isDigit() }
            val combinedValue = (firstDigit.toString() + lastDigit).toInt()
            total += combinedValue
        }
        return total
    }

    override fun part2(file: File): Int {
        var total = 0
        file.forEachLine {
            val firstDigit = it.getFirstDigit()
            val lastDigit = it.getLastDigit()
            val combinedValue = (firstDigit.toString() + lastDigit).toInt()
            total += combinedValue
        }
        return total
    }

    private fun String.getFirstDigit(): Int {
        var lowestIndex: Pair<Int, Int> = Pair(Int.MAX_VALUE, 0)
        for (i: Int in numbers.indices) {
            val alphabeticalIndex: Int = indexOf(numbers[i].first)
            val numericIndex: Int = indexOf(numbers[i].second.toString())
            if (numericIndex > -1 && numericIndex < lowestIndex.first) {
                lowestIndex = Pair(numericIndex, i)
            }
            if (alphabeticalIndex > -1 && alphabeticalIndex < lowestIndex.first) {
                lowestIndex = Pair(alphabeticalIndex, i)
            }
        }
        return lowestIndex.second
    }

    private fun String.getLastDigit(): Int {
        var highestIndex: Pair<Int, Int> = Pair(Int.MIN_VALUE, 0)
        for (i: Int in numbers.indices) {
            val alphabeticalIndex: Int = lastIndexOf(numbers[i].first)
            val numericIndex: Int = lastIndexOf(numbers[i].second.toString())
            if (numericIndex > -1 && numericIndex > highestIndex.first) {
                highestIndex = Pair(numericIndex, i)
            }
            if (alphabeticalIndex > -1 && alphabeticalIndex > highestIndex.first) {
                highestIndex = Pair(alphabeticalIndex, i)
            }
        }
        return highestIndex.second
    }

}
