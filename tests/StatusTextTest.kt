import treeOfLife.data.Month
import treeOfLife.data.TimePoint
import treeOfLife.data.Year
import treeOfLife.Visualization.StatusText
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatusTextTest {
    @Test
    fun `test status text with no overlapping periods and no age`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val birthMonth = TimePoint(Year(2023), Month.JANUARY)
        val result = StatusText.calculate(timePoint, birthMonth, emptyList())
        assertEquals("JANUARY, 2023", result)
    }

    @Test
    fun `test status text with no overlapping periods and age`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val birthMonth = TimePoint(Year(2013), Month.JANUARY)
        val result = StatusText.calculate(timePoint, birthMonth, emptyList())
        assertEquals("JANUARY, 2023 (age 10)", result)
    }

    @Test
    fun `test status text with one overlapping period and age`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val birthMonth = TimePoint(Year(2013), Month.JANUARY)
        val result = StatusText.calculate(timePoint, birthMonth, listOf("Work"))
        assertEquals("JANUARY, 2023 (age 10) - Work", result)
    }

    @Test
    fun `test status text with multiple overlapping periods and age`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val birthMonth = TimePoint(Year(2013), Month.JANUARY)
        val result = StatusText.calculate(timePoint, birthMonth, listOf("Work", "Education", "Hobby"))
        assertEquals("JANUARY, 2023 (age 10) - Work, Education, Hobby", result)
    }

    @Test
    fun `test age calculation same month`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val birthMonth = TimePoint(Year(2013), Month.JANUARY)
        val result = StatusText.calculate(timePoint, birthMonth, emptyList())
        assertEquals("JANUARY, 2023 (age 10)", result)
    }

    @Test
    fun `test age calculation before birth month`() {
        val timePoint = TimePoint(Year(2023), Month.JANUARY)
        val birthMonth = TimePoint(Year(2013), Month.FEBRUARY)
        val result = StatusText.calculate(timePoint, birthMonth, emptyList())
        assertEquals("JANUARY, 2023 (age 9)", result)
    }

    @Test
    fun `test age calculation after birth month`() {
        val timePoint = TimePoint(Year(2023), Month.FEBRUARY)
        val birthMonth = TimePoint(Year(2013), Month.JANUARY)
        val result = StatusText.calculate(timePoint, birthMonth, emptyList())
        assertEquals("FEBRUARY, 2023 (age 10)", result)
    }
}