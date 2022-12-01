import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AdventOfCodeTest {

    @Test
    fun `day 1 - a`() {
        assertEquals(60, Day1.day1Part1("day1/testinput.txt"))
    }

    @Test
    fun `day 1 - b`() {
        assertEquals(92, Day1.day1Part2("day1/testinput.txt"))
    }

}
