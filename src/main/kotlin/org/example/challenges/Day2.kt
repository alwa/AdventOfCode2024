package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File
import kotlin.math.absoluteValue

object Day2 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        var total = 0

        val reports = mutableListOf<IntArray>()
        val safeLevelDiffs = mutableListOf<Boolean>()
        file.forEachLine {
            val report = IntArray(5)
            val (first, second, third, fourth, fifth) = it.split(" ")
                .map { string -> string.trimStart().trimEnd().toInt() }
            report[0] = first
            report[1] = second
            report[2] = third
            report[3] = fourth
            report[4] = fifth
            reports.add(report)
        }
        for (reportIndex in reports.indices) {
            var safe = true
            for (levelIndex in reports[reportIndex].indices) {
                if (levelIndex < reports[reportIndex].size - 1) {
                    safe =
                        (reports[reportIndex][levelIndex] - reports[reportIndex][levelIndex + 1]).absoluteValue in 1..3
                } else {
                    safe = true
                }
                if (!safe) break
            }
            safeLevelDiffs.add(safe)
        }
        for (reportIndex in reports.indices) {
            var increasing: Boolean? = null
            var decreasing: Boolean? = null
            for (levelIndex in reports[reportIndex].indices) {
                if (levelIndex == reports[reportIndex].size - 1) {
                } else if (reports[reportIndex][levelIndex] < reports[reportIndex][levelIndex + 1] && increasing == null) {
                    decreasing = true
                } else if (reports[reportIndex][levelIndex] > reports[reportIndex][levelIndex + 1] && decreasing == null) {
                    increasing = true
                } else {
                    increasing = null
                    decreasing = null
                    break
                }
            }
            if (safeLevelDiffs[reportIndex] && (increasing == true || decreasing == true)) {
                total++
            }
        }
        return total
    }

    override fun part2(file: File): Int {
        return -1
    }

}
