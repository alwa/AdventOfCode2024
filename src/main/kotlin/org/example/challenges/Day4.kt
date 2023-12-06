package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day4 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        var total = 0
        file.forEachLine {
            var cardTotal = 0
            val sanitizedLine = it.replace("  ", " ")
            val winningNumbers: List<Int> = getWinningNumbers(sanitizedLine)
            val actualNumbers: List<Int> = getActualNumbers(sanitizedLine)
            for (actualNumber in actualNumbers) {
                if (winningNumbers.contains(actualNumber)) {
                    if (cardTotal == 0) {
                        cardTotal++
                    } else {
                        cardTotal *= 2
                    }
                }
            }
            total += cardTotal
        }
        return total
    }

    override fun part2(file: File): Int {
        // FIXME: Super slow...
        var numberOfLines = 0
        file.forEachLine {
            numberOfLines++
        }
        val scratchCards = Array(numberOfLines) { 1 }
        var total = 0
        var index = 0
        file.forEachLine {
            while (scratchCards[index] > 0) {
                scratchCards[index] -= 1
                total++
                // TODO: Replaces this messy and inefficient code
                val sanitizedLine = it.replace("  ", " ")
                val winningNumbers: List<Int> = getWinningNumbers(sanitizedLine)
                val actualNumbers: List<Int> = getActualNumbers(sanitizedLine)
                var matches = 1
                for (actualNumber in actualNumbers) {
                    if (winningNumbers.contains(actualNumber) && index + matches < numberOfLines) {
                        scratchCards[index + matches] += 1
                        matches++
                    }
                }
            }
            index++
        }
        return total
    }

    private fun getActualNumbers(sanitizedLine: String) =
        sanitizedLine.substring(sanitizedLine.indexOf("|") + 2, sanitizedLine.length).split(" ")
            .map { stringValue -> stringValue.toInt() }

    private fun getWinningNumbers(sanitizedLine: String): List<Int> =
        sanitizedLine.substring(sanitizedLine.indexOf(":") + 2, sanitizedLine.indexOf("|") - 1).split(" ")
            .map { stringValue -> stringValue.toInt() }

}
