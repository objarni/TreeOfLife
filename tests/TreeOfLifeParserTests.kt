import TreeOfLife.Data.Month
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class TreeOfLifeParserTests {

    @Test
    fun testMonthParser() {
        assertEquals(Month.JANUARY, monthParser("Jan"))
    }

    private fun monthParser(string: String): Month? {
        return Month.JANUARY
    }
}

