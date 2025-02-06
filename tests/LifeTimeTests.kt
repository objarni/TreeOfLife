import TreeOfLife.Data.Month
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import TreeOfLife.Data.educations
import TreeOfLife.Data.homes
import TreeOfLife.Data.overlappingPeriods
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

}

