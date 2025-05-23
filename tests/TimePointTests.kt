import treeOfLife.data.Month
import treeOfLife.data.TimePoint
import treeOfLife.data.Year
import treeOfLife.data.chronological
import kotlin.test.Test
import kotlin.test.assertFalse

class TimePointTests {
    @Test
    fun testChronologicalDifferentYearsOrdered() {
        val seventies = TimePoint(Year(1979), Month.JULY)
        val eighties = TimePoint(Year(1989), Month.JUNE)
        assert(chronological(seventies, eighties))
    }

    @Test
    fun testChronologicalDifferentYearsUnordered() {
        val year2k = TimePoint(Year(2000), Month.JULY)
        val eigtyNine = TimePoint(Year(1989), Month.JUNE)
        assertFalse(chronological(year2k, eigtyNine))
    }

    @Test
    fun testChronologicalSameYear() {
        val june = TimePoint(Year(1979), Month.JUNE)
        val july = TimePoint(Year(1979), Month.JULY)
        assert(chronological(june, july))
    }


    @Test
    fun testChronologicalSameTimePoint() {
        val june = TimePoint(Year(1979), Month.JUNE)
        assert(chronological(june, june))
    }

}

