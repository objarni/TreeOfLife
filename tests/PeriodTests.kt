import TreeOfLife.Box
import TreeOfLife.Month
import TreeOfLife.Period
import TreeOfLife.TimePoint
import TreeOfLife.Year
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.Rectangle

/*
a period in a persons life converted to a Box
 */

class PeriodTests {

    @Test
    fun testFromPeriodToBox() {
        val expeced = Box(
            rect = Rectangle(6, 4, 48, 5),
            color = Color.BLUE,
            text = "Test period"
        )
        val actual = Period(
            TimePoint(Year(1979), Month.JULY),
            TimePoint(Year(1983), Month.JULY),
            "Test period"
        )
            .toBox(
                Color.BLUE,
                y=4,
                monthOfBirth = TimePoint(
                    Year(1979), Month.JANUARY
                )
            )
        assertEquals(expeced, actual)
    }
}
