import treeOfLife.Visualization.TextBlock
import treeOfLife.Data.Month
import treeOfLife.Data.Period
import treeOfLife.Data.TimePoint
import treeOfLife.Data.Year
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
