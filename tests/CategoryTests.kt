import TreeOfLife.Domain.Month
import TreeOfLife.Domain.Period
import TreeOfLife.Domain.TimePoint
import TreeOfLife.Domain.Year
import TreeOfLife.Domain.textBlocksForPeriods
import TreeOfLife.homePeriods
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
        val boxes = textBlocksForPeriods(
            periods, baseY = 0, color = Color.BLUE, birthMonth = TimePoint(
            Year(1979), Month.JULY)
        )
        assertEquals(0, boxes[0].rect.y)
        assertEquals(0, boxes[1].rect.y)
    }

    @Test
    fun testTwoOverlapping() {
        val periods = listOf(
            Period(TimePoint(Year(2020), Month.JANUARY), TimePoint(Year(2020), Month.MARCH), "Test period 1"),
            Period(TimePoint(Year(2020), Month.FEBRUARY), TimePoint(Year(2020), Month.MAY), "Test period 2")
        )
        val boxes = textBlocksForPeriods(
            periods, baseY = 0, color = Color.RED, birthMonth = TimePoint(
            Year(2000), Month.JANUARY)
        )
        assertEquals(0, boxes[0].rect.y)
        assertEquals(240, boxes[0].rect.x)
        assertEquals(1, boxes[1].rect.y)
        assertEquals(241, boxes[1].rect.x)
        assertEquals(Color.RED, boxes[1].color)
    }

    @Test
    fun test3InZigZagFormation() {
        val periods = listOf(
            Period(TimePoint(Year(2020), Month.JANUARY), TimePoint(Year(2020), Month.MARCH), "Test period 1"),
            Period(TimePoint(Year(2020), Month.FEBRUARY), TimePoint(Year(2021), Month.MARCH), "Test period 1"),
            Period(TimePoint(Year(2021), Month.MAY), TimePoint(Year(2025), Month.MAY), "Test period 3")
        )
        val boxes = textBlocksForPeriods(
            periods, baseY = 0, color = Color.BLUE, birthMonth = TimePoint(
            Year(1979), Month.JULY)
        )
        val xs = boxes.map { it.rect.x }
        val xIsSorted = xs.zipWithNext().all { (a, b) -> a < b }
        assert(xIsSorted)
        assertEquals(intArrayOf(0, 1, 0).toList(), boxes.map { it.rect.y })
    }

    @Test
    fun homeBoxesAreSortedInX() {
        val boxes = textBlocksForPeriods(
            homePeriods(), baseY = 0, color = Color.BLUE, birthMonth = TimePoint(
            Year(1979), Month.JULY)
        )
        val xs = boxes.map { it.rect.x }
        val xIsSorted = xs.zipWithNext().all { (a, b) -> a < b }
        assert(xIsSorted)
    }
}

