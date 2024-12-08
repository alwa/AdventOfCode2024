package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File
import kotlin.math.absoluteValue

object Day3 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        var total = 0
        file.forEachLine {
            val validInstructions = Regex("""mul\((\d{1,3}),(\d{1,3})\)""").findAll(it)
            for (validInstruction in validInstructions.iterator()) {
                total += validInstruction.destructured.component1().toInt() * validInstruction.destructured.component2().toInt()
            }
        }
        return total
    }

    override fun part2(file: File): Int {
        var total = 0
        file.forEachLine {
        }
        return total
    }

}
