package org.example.challenges

import org.example.TwoPartChallenge
import org.example.util.parseAllLinesFromFile
import java.io.File

object Day3 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        val matrix = createEngineSchematic(file.parseAllLinesFromFile())
        val booleanMatrix = createBooleanMatrix(matrix) { c -> c != '.' }
        var total = 0
        for (i in matrix.indices) {
            total += matrix.part1Count(
                i,
                checkBelow = i < matrix.size - 1,
                checkAbove = i > 0,
                booleanMatrix = booleanMatrix,
            )
        }
        return total
    }

    override fun part2(file: File): Int {
        return -1
    }

    private fun createBooleanMatrix(matrix: Array<Array<Char>>, eval: (Char) -> Boolean): Array<Array<Boolean>> {
        val booleanMatrix = Array(matrix.size) { Array(matrix[0].size) { false } }
        for (y in matrix.indices) {
            for (x in matrix[y].indices) {
                booleanMatrix[y][x] = eval(matrix[y][x])
            }
        }
        return booleanMatrix
    }

    private fun Array<Array<Char>>.part1Count(
        y: Int,
        checkBelow: Boolean,
        checkAbove: Boolean,
        booleanMatrix: Array<Array<Boolean>>
    ): Int {
        var total = 0
        var number = CharArray(0)
        var hasAdjacentSymbol = false
        for (x in get(y).indices) {
            if (get(y)[x].isDigit()) {
                if (number.isEmpty() && x > 0) {
                    hasAdjacentSymbol = booleanMatrix[y][x - 1]
                }
                number += get(y)[x]
                when {
                    checkBelow && booleanMatrix[y + 1][x] -> hasAdjacentSymbol = true
                    checkBelow && x > 0 && booleanMatrix[y + 1][x - 1] -> hasAdjacentSymbol = true
                    checkBelow && x < get(y).size - 1 && booleanMatrix[y + 1][x + 1] -> hasAdjacentSymbol = true
                    checkAbove && x > 0 && booleanMatrix[y - 1][x - 1] -> hasAdjacentSymbol = true
                    checkAbove && booleanMatrix[y - 1][x] -> hasAdjacentSymbol = true
                    checkAbove && x < get(y).size - 1 && booleanMatrix[y - 1][x + 1] -> hasAdjacentSymbol = true
                }
            } else {
                if (number.isNotEmpty() && booleanMatrix[y][x]) {
                    hasAdjacentSymbol = true
                }
                if (hasAdjacentSymbol) {
                    val identifiedNumberToAdd = String(number).toInt()
                    total += identifiedNumberToAdd
                    hasAdjacentSymbol = false
                }
                number = CharArray(0)
            }
            if (get(y)[x].isDigit() && x == get(y).lastIndex) {
                if (hasAdjacentSymbol) {
                    val identifiedNumberToAdd = String(number).toInt()
                    total += identifiedNumberToAdd
                    hasAdjacentSymbol = false
                }
            }
        }
        return total
    }

    private fun createEngineSchematic(lines: List<String>): Array<Array<Char>> {
        val matrix = Array(lines.size) { Array(lines[0].length) { 0.toChar() } }
        for ((index, line) in lines.withIndex()) {
            for (i: Int in line.indices) {
                matrix[index][i] = line[i]
            }
        }
        return matrix
    }

}
