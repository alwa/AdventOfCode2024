package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File
import kotlin.math.absoluteValue

object Day2 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        var total = 0
        val safeLevelDiffs = mutableListOf<Boolean>()
        val reports = reports(file)
        for (reportIndex in reports.indices) {
            safeLevelDiffs.add(isLevelsDifferencesSafe(reports, reportIndex))
        }
        for (reportIndex in reports.indices) {
            val levelSafe = isLevelsSafe(reports, reportIndex, safeLevelDiffs)
            total += if (levelSafe) 1 else 0
        }
        return total
    }

    override fun part2(file: File): Int {
        var total = 0
        val reports = reports(file)
        // TODO: Populate increase / decrease matrix
        for (index in reports.indices) {
            outer@ for (indexToRemove in reports.indices) {
                val reducedReport = reports[index].filterIndexed { index, _ -> index != indexToRemove }.toMutableList()
                reportLoop@ for (reportIndex in reducedReport.indices) {
                    val levelDiffSafe = isLevelDifferencesSafe(reducedReport)
                    if (!levelDiffSafe) break@reportLoop
                    val levelSafe = isLevelSafe(reducedReport)
                    if (levelSafe) {
                        total += 1
                        break@outer
                    }
                }
            }
        }
        return total
    }

    private fun reports(file: File): MutableList<IntArray> {
        val reports = mutableListOf<IntArray>()
        file.forEachLine {
            val numbers = it.split(" ")
                .map { string -> string.trimStart().trimEnd().toInt() }
            val report = IntArray(numbers.size)
            for (i in report.indices) {
                report[i] = numbers[i]
            }
            reports.add(report)
        }
        return reports
    }

    private fun isLevelsSafe(
        reports: MutableList<IntArray>,
        reportIndex: Int,
        safeLevelDiffs: MutableList<Boolean>,
    ): Boolean {
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
        return safeLevelDiffs[reportIndex] && (increasing == true || decreasing == true)
    }

    private fun isLevelSafe(
        reports: MutableList<Int>,
    ): Boolean {
        var increasing: Boolean? = null
        var decreasing: Boolean? = null
        for (levelIndex in reports.indices - 1) {
            if (levelIndex == reports.size - 1) {
            } else if (reports[levelIndex] < reports[levelIndex + 1] && increasing == null) {
                decreasing = true
            } else if (reports[levelIndex] > reports[levelIndex + 1] && decreasing == null) {
                increasing = true
            } else {
                increasing = null
                decreasing = null
                break
            }
        }
        return (increasing == true || decreasing == true)
    }

    private fun isLevelDifferencesSafe(reports: MutableList<Int>): Boolean {
        var safe = true
        for (levelIndex in 0 until reports.size) {
            if (levelIndex < reports.size - 1) {
                safe =
                    (reports[levelIndex] - reports[levelIndex + 1]).absoluteValue in 1..3
            } else {
                safe = true
            }
            if (!safe) break
        }
        return safe
    }

    private fun isLevelsDifferencesSafe(reports: MutableList<IntArray>, reportIndex: Int): Boolean {
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
        return safe
    }

}
