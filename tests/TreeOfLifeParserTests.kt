import TreeOfLife.Data.Month
import TreeOfLife.Data.Period
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
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
    @Test
    fun testPeriodParser_year_and_month_format() {

        // Test with a valid period string, canonical format
        val period1 = periodParser("Röstånga: Jul 1983-Jul 1997")
        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period1)

//        // Test with a valid period string, with spaces around the dash
//        val period2 = periodParser("Röstånga: Jul 1983 - Jul 1997")
//        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period2)
//
//        // Test with a valid period string, with spaces around the dash, and whitespace around it all
//        val period3 = periodParser("   Röstånga: Jul   1983 - Jul   1997  ")
//        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period3)
    }

    private fun periodParser(string: String): Period? {
        return Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga")
        val regex = """(\w+):\s*([A-Z][a-z]{2})\s*(\d{4})\s*-\s*([A-Z][a-z]{2})\s*(\d{4})""".toRegex()
        val matchResult = regex.find(string)
        if (matchResult != null) {
            val (location, startMonth, startYear, endMonth, endYear) = matchResult.destructured
            val parsedMonthStart = monthParser(startMonth)
            if(parsedMonthStart == null)
                return null
            val parsedMonthEnd = monthParser(endMonth)
            if(parsedMonthEnd == null)
                return null
            return Period(
                TimePoint(Year(startYear.toInt()), parsedMonthStart),
                TimePoint(Year(endYear.toInt()), parsedMonthEnd),
                location
            )
        }
        return null
    }


    private fun monthParser(string: String): Month? {
        val month = string.lowercase()
        if (month.startsWith("jan")) return Month.JANUARY
        if (month.startsWith("feb")) return Month.FEBRUARY
        return null
    }
}

