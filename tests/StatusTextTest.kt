import TreeOfLife.Data.Month
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import TreeOfLife.Visualization.StatusText
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatusTextTest {
    @Test
    fun `test status text with no overlapping periods`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val result = StatusText.calculate(timePoint, emptyList())
        assertEquals("JANUARY, 2023", result)
    }

    @Test
    fun `test status text with one overlapping period`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val result = StatusText.calculate(timePoint, listOf("Work"))
        assertEquals("JANUARY, 2023 - Work", result)
    }

    @Test
    fun `test status text with multiple overlapping periods`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val result = StatusText.calculate(timePoint, listOf("Work", "Education", "Hobby"))
        assertEquals("JANUARY, 2023 - Work, Education, Hobby", result)
    }
}