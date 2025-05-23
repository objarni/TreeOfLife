import treeOfLife.data.*
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

    @Test
    fun testTimePoint_parser() {
        val timePoint1 = timePointParser("Jul 1983")
        assertEquals(TimePoint(Year(1983), Month.JULY), timePoint1)

        val timePoint2 = timePointParser("Jul 1997")
        assertEquals(TimePoint(Year(1997), Month.JULY), timePoint2)

        val timePoint3 = timePointParser("   Jul   1997  ")
        assertEquals(TimePoint(Year(1997), Month.JULY), timePoint3)

        val timePoint4 = timePointParser("sososo")
        assertNull(timePoint4)
    }

    // Period parser - long format
    @Test
    fun testPeriodParser_long_format() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        // Test with a valid period string, canonical format
        val period1 = periodParser("Röstånga: Jul 1983-Jul 1997", currentTimePoint)
        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period1)

        // Test with a valid period string, with spaces around the dash
        val period2 = periodParser("Röstånga: Jul 1983 - Jul 1997", currentTimePoint)
        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period2)

        // Test with a valid period string, with spaces around the dash, and whitespace around it all
        val period3 = periodParser("   Röstånga: Jul   1983 - Jul   1997  ", currentTimePoint)
        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period3)
    }

    // Period parser - short format
    // Röstånga: Jul-Jul 1997
    // Röstånga: Jul - Jul 1997
    //   Röstånga : Jun - Aug   1998
    @Test
    fun testPeriodParser_short_format() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val period1 = periodParser("Röstånga: Jul-Aug 1997", currentTimePoint)
        assertEquals(Period(TimePoint(Year(1997), Month.JULY), TimePoint(Year(1997), Month.AUGUST), "Röstånga"), period1)

        val period2 = periodParser("Röstånga: May - Jul 1997", currentTimePoint)
        assertEquals(Period(TimePoint(Year(1997), Month.MAY), TimePoint(Year(1997), Month.JULY), "Röstånga"), period2)

        val period3 = periodParser("   Röstånga: Jul   - Dec   2003  ", currentTimePoint)
        assertEquals(Period(TimePoint(Year(2003), Month.JULY), TimePoint(Year(2003), Month.DECEMBER), "Röstånga"), period3)
    }

    @Test
    fun testPeriodParser_spaces_in_name() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val period = periodParser("Röst Ånga: Jul 1983-Jul 1997", currentTimePoint)
        assertEquals(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röst Ånga"), period)
    }

    @Test
    fun testCategoryParser_single_period() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val actualCategory = categoryParser("---Homes fulltime---\nRöstånga: Jul 1983-Jul 1997", currentTimePoint)
        assertEquals(
            Category(
                "Homes fulltime",
                listOf(Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"))
            ),
            actualCategory
        )
    }

    @Test
    fun `test CategoryParser with two periods`() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val actualCategory = categoryParser(
            """---Homes---
Röstånga: Jul 1983-Jul 1997
Klippan: Jun 1996-Jul 1997""", currentTimePoint
        )
        assertEquals(
            Category(
                "Homes",
                listOf(
                    Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"),
                    Period(TimePoint(Year(1996), Month.JUNE), TimePoint(Year(1997), Month.JULY), "Klippan"),
                )
            ),
            actualCategory
        )
    }

    @Test
    fun testCategoriesParser() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val categories: List<Category> = categoriesParser(
            """---Homes---

  Röstånga: Jul 1983-Jul 1997

  Klippan: Jun 1996-Jul 1997



###


   ---Computers---
  C64: Jul 1986-Jul 1995
  
  Amiga 500: Feb 1992-Feb 2000
""", currentTimePoint
        )
        assertEquals(
            listOf(
                Category(
                    "Homes",
                    listOf(
                        Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"),
                        Period(TimePoint(Year(1996), Month.JUNE), TimePoint(Year(1997), Month.JULY), "Klippan"),
                    )
                ),
                Category(
                    "Computers",
                    listOf(
                        Period(TimePoint(Year(1986), Month.JULY), TimePoint(Year(1995), Month.JULY), "C64"),
                        Period(
                            TimePoint(Year(1992), Month.FEBRUARY),
                            TimePoint(Year(2000), Month.FEBRUARY),
                            "Amiga 500"
                        ),
                    )
                )
            ),
            categories
        )
    }

    @Test
    fun `test treeOfLife parser`() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val input = """
            Birth month: Jan 1983
            Name: Test Person

            ---Homes---
            Röstånga: Jul 1983-Jul 1997
        """.trimIndent()
        val treeOfLifeData = treeOfLifeParser(input, currentTimePoint)
        val birthMonth = treeOfLifeData.birthMonth
        val name = treeOfLifeData.name
        val categories = treeOfLifeData.categories
        assertEquals(TimePoint(Year(1983), Month.JANUARY), birthMonth)
        assertEquals("Test Person", name)
        assertEquals(
            listOf(
                Category(
                    "Homes",
                    listOf(
                        Period(TimePoint(Year(1983), Month.JULY), TimePoint(Year(1997), Month.JULY), "Röstånga"),
                    )
                )
            ),
            categories
        )
    }

    @Test
    fun testPeriodParser_present_format() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val period1 = periodParser("Göteborg: Aug 2024-", currentTimePoint)
        assertEquals(Period(TimePoint(Year(2024), Month.AUGUST), currentTimePoint, "Göteborg"), period1)

        val period2 = periodParser("Göteborg: Aug 2024 - ", currentTimePoint)
        assertEquals(Period(TimePoint(Year(2024), Month.AUGUST), currentTimePoint, "Göteborg"), period2)

        val period3 = periodParser("   Göteborg: Aug   2024 -  ", currentTimePoint)
        assertEquals(Period(TimePoint(Year(2024), Month.AUGUST), currentTimePoint, "Göteborg"), period3)
    }

}

