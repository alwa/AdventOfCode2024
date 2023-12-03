package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun `Part 1`() {
        assertEquals(4361, Day3.part1(File(ClassLoader.getSystemResource("day3/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(467835, Day3.part2(File(ClassLoader.getSystemResource("day3/testinput.txt").file)))
    }

}
