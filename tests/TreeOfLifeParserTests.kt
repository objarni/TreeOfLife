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

    // Test data for the period parser
    // Röstånga: Jul 1983-Jul 1997
    // Röstånga: Jul 1983 - Jul 1997
    //   Röstånga : Jun - Aug   1998
    


    private fun monthParser(string: String): Month? {
        val month = string.lowercase()
        if (month.startsWith("jan")) return Month.JANUARY
        if (month.startsWith("feb")) return Month.FEBRUARY
        return null
    }
}

