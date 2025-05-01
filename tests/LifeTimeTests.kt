import treeOfLife.data.Month
import treeOfLife.data.TimePoint
import treeOfLife.data.Year
import treeOfLife.data.educations
import treeOfLife.data.homes
import treeOfLife.data.overlappingPeriods
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

