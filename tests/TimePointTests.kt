import TreeOfLife.Data.Month
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
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

    private fun chronological(before: TimePoint, after: TimePoint): Boolean {
        if(before.year.value == after.year.value) {
            return before.month.value < after.month.value
        }
        return before.year.value < after.year.value
    }
}