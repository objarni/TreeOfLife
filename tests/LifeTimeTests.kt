import treeOfLife.Data.Month
import treeOfLife.Data.TimePoint
import treeOfLife.Data.Year
import treeOfLife.Data.educations
import treeOfLife.Data.homes
import treeOfLife.Data.overlappingPeriods
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LifeTimeTests {
    @Test
    fun test10YearsOldPeriods() {
        val currentTimePoint = TimePoint(Year(2025), Month.APRIL)
        val expected = listOf(
            "Röstånga",
            "Röstånga skola"
        )
        val lifeTime = listOf(
            homes(currentTimePoint),
            educations(currentTimePoint)
        )
        val actual = overlappingPeriods(
            TimePoint(Year(1989), Month.OCTOBER),
            lifeTime
        )
        assertEquals(expected, actual)
    }

}

