import TreeOfLife.GraphicsPanel.TextBlock
import TreeOfLife.Domain.Month
import TreeOfLife.Domain.Period
import TreeOfLife.Domain.TimePoint
import TreeOfLife.Domain.Year
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.Rectangle

/*
a period in a persons life converted to a Box
 */

class PeriodTests {

    @Test
    fun testFromPeriodToBlock() {
        val expeced = TextBlock(
            rect = Rectangle(6, 4, 48, 1),
            color = Color.BLUE,
            text = "Test period"
        )
        val actual = Period(
            TimePoint(Year(1979), Month.JULY),
            TimePoint(Year(1983), Month.JULY),
            "Test period"
        )
            .toBlock(
                Color.BLUE,
                y=4,
                monthOfBirth = TimePoint(
                    Year(1979), Month.JANUARY
                )
            )
        assertEquals(expeced, actual)
    }
}
