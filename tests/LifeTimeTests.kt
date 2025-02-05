import TreeOfLife.Data.Category
import TreeOfLife.Data.Month
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import TreeOfLife.Data.educations
import TreeOfLife.Data.homes
import TreeOfLife.chronological
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LifeTimeTests {
    @Test
    fun test10YearsOldPeriods() {
        val expected = listOf(
            "Röstånga",
            "Röstånga skola"
        )
        val lifeTime = listOf(
            homes(),
            educations()
        )
        val actual = overlappingPeriods(
            TimePoint(Year(1989), Month.OCTOBER),
            lifeTime
        )
        assertEquals(expected, actual)
    }

    private fun overlappingPeriods(
        point: TimePoint,
        categories: List<Category>
    ): List<String> {
        return categories.flatMap { category ->
            category.periods.filter { period ->
                chronological(period.start, point) && chronological(point, period.end)
            }.map { it.text }
        }
    }
}

