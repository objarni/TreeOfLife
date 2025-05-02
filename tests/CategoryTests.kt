import treeOfLife.data.Month
import treeOfLife.data.Period
import treeOfLife.data.TimePoint
import treeOfLife.data.Year
import treeOfLife.data.textBlocksForPeriods
import treeOfLife.data.homes
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color

class CategoryTests {
    @Test
    fun testNonOverlapping() {
        val periods = listOf(
            Period(TimePoint(Year(2020), Month.JANUARY), TimePoint(Year(2020), Month.FEBRUARY), "Test period 1"),
            Period(TimePoint(Year(2020), Month.FEBRUARY), TimePoint(Year(2020), Month.MARCH), "Test period 2")
        )
        val blocks = textBlocksForPeriods(
            periods, baseY = 0, color = Color.BLUE, birthMonth = TimePoint(
            Year(1979), Month.JULY)
        )
        assertEquals(0, blocks[0].rect.y)
        assertEquals(0, blocks[1].rect.y)
    }

    @Test
    fun testTwoOverlapping() {
        val periods = listOf(
            Period(TimePoint(Year(2020), Month.JANUARY), TimePoint(Year(2020), Month.MARCH), "Test period 1"),
            Period(TimePoint(Year(2020), Month.FEBRUARY), TimePoint(Year(2020), Month.MAY), "Test period 2")
        )
        val blocks = textBlocksForPeriods(
            periods, baseY = 0, color = Color.RED, birthMonth = TimePoint(
            Year(2000), Month.JANUARY)
        )
        assertEquals(0, blocks[0].rect.y)
        assertEquals(240, blocks[0].rect.x)
        assertEquals(4, blocks[1].rect.y)
        assertEquals(241, blocks[1].rect.x)
        assertEquals(Color.RED, blocks[1].color)
    }

    @Test
    fun test3InZigZagFormation() {
        val periods = listOf(
            Period(TimePoint(Year(2020), Month.JANUARY), TimePoint(Year(2020), Month.MARCH), "Test period 1"),
            Period(TimePoint(Year(2020), Month.FEBRUARY), TimePoint(Year(2021), Month.MARCH), "Test period 1"),
            Period(TimePoint(Year(2021), Month.MAY), TimePoint(Year(2025), Month.MAY), "Test period 3")
        )
        val blocks = textBlocksForPeriods(
            periods, baseY = 0, color = Color.BLUE, birthMonth = TimePoint(
            Year(1979), Month.JULY)
        )
        val xs = blocks.map { it.rect.x }
        val xIsSorted = xs.zipWithNext().all { (a, b) -> a < b }
        assert(xIsSorted)
        assertEquals(intArrayOf(0, 4, 0).toList(), blocks.map { it.rect.y })
    }

    @Test
    fun homeBlocksAreSortedInX() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val blocks = textBlocksForPeriods(
            homes(currentTimePoint).periods, baseY = 0, color = Color.BLUE, birthMonth = TimePoint(
            Year(1979), Month.JULY)
        )
        val xs = blocks.map { it.rect.x }
        val xIsSorted = xs.zipWithNext().all { (a, b) -> a < b }
        assert(xIsSorted)
    }
}

