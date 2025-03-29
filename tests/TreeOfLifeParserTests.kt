import TreeOfLife.Data.Month
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test
import kotlin.test.assertNull

class TreeOfLifeParserTests {

    @Test
    fun testMonthParser() {
        assertEquals(Month.JANUARY, monthParser("Jan"))
        assertEquals(Month.FEBRUARY, monthParser("Feb"))
        assertNull(monthParser("sososo"))
    }

    private fun monthParser(string: String): Month? {
        val month = string.lowercase()
        if (month.startsWith("jan")) return Month.JANUARY
        if (month.startsWith("feb")) return Month.FEBRUARY
        return null
    }
}

