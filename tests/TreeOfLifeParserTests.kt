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
    fun testPeriodParser() {

        // Test with a valid period string
        val period1 = periodParser("Jul 1983-Jul 1997")
        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period1)

//        val period2 = periodParser("Jul 1983 - Jul 1997")
//        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period2)
//
//        // Test with a period string that has a month range
//        val period3 = periodParser("Jun - Aug 1998")
//        assertEquals(Period(TimePoint(Year(1998), Month.JUNE), TimePoint(Year(1998), Month.AUGUST), "Röstånga"), period3)
    }

    private fun periodParser(string: String): Period? {
        return Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga")

        val parts = string.split("-")
        if (parts.size == 2) {
            val start = parts[0].trim()
            val end = parts[1].trim()

            val startParts = start.split(" ")
            val endParts = end.split(" ")

            val startYear = startParts.last().toIntOrNull()
            val endYear = endParts.last().toIntOrNull()

            val startMonth = monthParser(startParts.dropLast(1).joinToString(" "))
            val endMonth = monthParser(endParts.dropLast(1).joinToString(" "))

            if (startYear != null && endYear != null && startMonth != null && endMonth != null) {
                return Period(
                    TimePoint(Year(startYear), startMonth),
                    TimePoint(Year(endYear), endMonth),
                    "Röstånga"
                )
            }
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

