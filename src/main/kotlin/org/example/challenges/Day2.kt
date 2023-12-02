package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day2 : TwoPartChallenge<Int, Int> {

    private val bag: Map<String, Int> = mapOf(
        Pair("red", 12),
        Pair("green", 13),
        Pair("blue", 14),
    )

    override fun part1(file: File): Int {
        var total = 0
        file.forEachLine {
            val gameId = it.substring(startIndex = 5, endIndex = it.indexOf(':')).toInt()
            var subSequence = it.subSequence(it.indexOf(':') + 2, it.length)
            var okToContinue = true
            while (okToContinue && subSequence.contains(';')) {
                val revelations = subSequence.split("; ")
                if (areRevelationsOk(revelations)) {
                    total += gameId
                } else {
                    okToContinue = false
                }
                subSequence = subSequence.subSequence(subSequence.indexOf(';'), subSequence.length)
            }
        }
        return total
    }

    override fun part2(file: File): Int {
        var total = 0
        file.forEachLine {
            var subSequence = it.subSequence(it.indexOf(':') + 2, it.length)
            var okToContinue = true
            var minBlue = 0
            var minRed = 0
            var minGreen = 0
            while (okToContinue && subSequence.contains(';')) {
                val revelations = subSequence.split("; ")
                val minBlueInGame = calculateMaxColor(revelations, "blue")
                if (minBlueInGame > minBlue) {
                    minBlue = minBlueInGame
                }
                val minRedInGame = calculateMaxColor(revelations, "red")
                if (minRedInGame > minRed) {
                    minRed = minRedInGame
                }
                val minGreenInGame = calculateMaxColor(revelations, "green")
                if (minGreenInGame > minGreen) {
                    minGreen = minGreenInGame
                }
                subSequence = subSequence.subSequence(subSequence.indexOf("; ") + 2, subSequence.length)
                if (subSequence == "") {
                    okToContinue = false
                }
            }
            total += minBlue * minRed * minGreen
        }
        return total
    }

    private fun calculateMaxColor(revelations: List<String>, color: String): Int {
        var numberSoFar = -1
        for (revelation in revelations) {
            val revelationParts = revelation.split(", ")
            for (revelationPart in revelationParts) {
                if (revelationPart.parseColor() == color && revelationPart.parseNumber() > numberSoFar) {
                    numberSoFar = revelationPart.parseNumber()
                }
            }
        }
        return numberSoFar
    }

    private fun areRevelationsOk(revelations: List<String>): Boolean {
        for (revelation in revelations) {
            if (!revelation.isRevelationOk()) {
                return false
            }
        }
        return true
    }

    private fun String.isRevelationOk(): Boolean {
        if (isBlank()) {
            return false
        }
        val revelationParts = split(", ")
        for (revelationPart in revelationParts) {
            if (!revelationPart.isOk()) {
                return false
            }
        }
        return true
    }

    private fun String.isOk(): Boolean {
        val number = parseNumber()
        val color = parseColor()
        return bag[color]!! >= number
    }

    private fun String.parseColor() = substring(indexOf(' ') + 1, length)

    private fun String.parseNumber() = substring(0, indexOf(' ')).toInt()

}
