import treeOfLife.Visualization.TextBlock
import treeOfLife.data.Month
import treeOfLife.data.Period
import treeOfLife.data.TimePoint
import treeOfLife.data.Year
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
            rect = Rectangle(6, 4, 48, 2),
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
