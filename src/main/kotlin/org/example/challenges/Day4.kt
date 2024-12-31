package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day4 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        var total = 0
        var lineLength = -1
        val lines = mutableListOf<String>()
        file.forEachLine {
            if (lineLength == -1) {
                lineLength = it.length
            }
            lines.add(it)
        }
        val rows = mutableListOf<String>()
        repeat(lines.size) {
            rows.add("")
        }
        for (i in 0 until lines.size) {
            rows[i] += lines[i]
        }
        val diagonals = mutableListOf<String>()
        for (i in 0 until lines.size) {
            var diag = ""
            var x = i
            var y = 0
            while (x < lines.size && y < lineLength) {
                diag += lines[x][y]
                x++
                y++
            }
            diagonals.add(diag)
        }

        for (j in 1 until lineLength) {
            var diag = ""
            var x = 0
            var y = j
            while (x < lines.size && y < lineLength) {
                diag += lines[x][y]
                x++
                y++
            }
            diagonals.add(diag)
        }
        val pattern = Regex("X.*?M.*?A.*?S")
        for (i in 0 until lines.size) {
            total += pattern.findAll(lines[i]).count()
            total += pattern.findAll(lines[i].reversed()).count()
        }
        for (i in 0 until lines.size) {
            total += pattern.findAll(rows[i]).count()
            total += pattern.findAll(rows[i].reversed()).count()
        }
        for (i in 0 until diagonals.size) {
            total += pattern.findAll(diagonals[i]).count()
            total += pattern.findAll(diagonals[i].reversed()).count()
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
