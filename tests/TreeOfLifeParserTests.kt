import TreeOfLife.Data.Month
import TreeOfLife.Data.Period
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import TreeOfLife.Data.monthParser
import TreeOfLife.Data.periodParser
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test
import kotlin.test.assertNull

class TreeOfLifeParserTests {

    @Test
    fun testMonthParser() {
        assertEquals(Month.JANUARY, monthParser("Jan"))
        assertEquals(Month.FEBRUARY, monthParser("Feb"))
        assertEquals(Month.MARCH, monthParser("Mar"))
        assertEquals(Month.APRIL, monthParser("Apr"))
        assertEquals(Month.MAY, monthParser("May"))
        assertEquals(Month.JUNE, monthParser("Jun"))
        assertEquals(Month.JULY, monthParser("Jul"))
        assertEquals(Month.AUGUST, monthParser("Aug"))
        assertEquals(Month.SEPTEMBER, monthParser("Sep"))
        assertEquals(Month.OCTOBER, monthParser("Oct"))
        assertEquals(Month.NOVEMBER, monthParser("Nov"))
        assertEquals(Month.DECEMBER, monthParser("Dec"))
        assertNull(monthParser("sososo"))
    }

    // Period parser - long format
    @Test
    fun testPeriodParser_year_and_month_long_format() {
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

    // Period parser - short format
    // Röstånga: Jul-Jul 1997
    // Röstånga: Jul - Jul 1997
    //   Röstånga : Jun - Aug   1998
//    @Test
//    fun testPeriodParser_year_and_month_short_format() {
//        val period1 = periodParserShortFormat("Röstånga: Jul 1983-Jul 1997")
//        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period1)
//
//        val period2 = periodParserShortFormat("Röstånga: Jul - Jul 1997")
//        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period2)
//
//        val period3 = periodParserShortFormat("   Röstånga: Jul   - Jul   1997  ")
//        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period3)
//    }
//
//     fun periodParserShortFormat(string: String): Period? {
//        return Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga")
//    }
}

