package org.example.challenges

import org.example.TwoPartChallenge
import org.example.util.parseAllLinesFromFile
import java.io.File

object Day6 : TwoPartChallenge<Long, Long> {

    override fun part1(file: File): Long {
        val allLines = file.parseAllLinesFromFile()
        val timeParts: List<Long> = allLines[0].split("\\s+".toRegex()).drop(1).map { string -> string.toLong() }
        val distanceParts: List<Long> = allLines[1].split("\\s+".toRegex()).drop(1).map { string -> string.toLong() }
        return timeParts.indices.map { i -> race(recordTime = timeParts[i], raceDistance = distanceParts[i]) }
            .reduce { acc, i -> acc * i }
    }

    override fun part2(file: File): Long {
        val allLines = file.parseAllLinesFromFile()
        val time = allLines[0].split(":").drop(1)[0].replace(" ", "").toLong()
        val distance = allLines[1].split(":").drop(1)[0].replace(" ", "").toLong()
        return race(recordTime = time, raceDistance = distance)
    }

    private fun race(recordTime: Long, raceDistance: Long): Long {
        var result = 0L
        for (timeHeldInMs in 0..recordTime) {
            val distance = timeHeldInMs * (recordTime - timeHeldInMs)
            if (distance > raceDistance) {
                result++
            }
        }
        return result
    }

}
