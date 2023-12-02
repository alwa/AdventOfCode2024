package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun `Part 1`() {
        assertEquals(8, Day2.part1(File(ClassLoader.getSystemResource("day2/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(2286, Day2.part2(File(ClassLoader.getSystemResource("day2/testinput.txt").file)))
    }

}
