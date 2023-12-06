package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun `Part 1`() {
        assertEquals(288, Day6.part1(File(ClassLoader.getSystemResource("day6/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(71503, Day6.part2(File(ClassLoader.getSystemResource("day6/testinput.txt").file)))
    }

}
