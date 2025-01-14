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
            rect = Rectangle(0, 4, 48, 5),
            color = Color.BLUE
        )
        val actual = Period(
            TimePoint(Year(1979), Month(7)),
            TimePoint(Year(1983), Month(7)),
            "Test period"
        )
            .toBox(
                Color.BLUE,
                y=4,
                monthOfBirth = TimePoint(
                    Year(1979), Month(7)
                )
            )
        assertEquals(expeced, actual)
    }
}
