package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `Part 1`() {
        assertEquals(11, Day1.part1(File(ClassLoader.getSystemResource("day1/testinput.txt").file)))
    }

//    @Test
//    fun `Part 2`() {
//        assertEquals(281, Day1.part2(File(ClassLoader.getSystemResource("day1/testinput2.txt").file)))
//    }

}
