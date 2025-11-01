import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import treeOfLife.Visualization.timePointAtPosition
import treeOfLife.data.Month
import treeOfLife.data.TimePoint
import treeOfLife.data.Year

class TimelinePanelTests {

    @Test
    fun `test that x position 0 means birth month`() {
        val birthMonth = TimePoint(Year(1979), Month.JULY)
        val xPos = 0
        assertEquals(birthMonth, timePointAtPosition(xPos, birthMonth))
   }

    @Test
    fun `test that x position 11 means almost a year post birth`() {
        val birthMonth = TimePoint(Year(1979), Month.JULY)
        val xPos = 11
        assertEquals(TimePoint(Year(1980), Month.AUGUST),
            timePointAtPosition(xPos, birthMonth))
   }
}
