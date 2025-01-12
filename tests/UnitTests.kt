import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SimpleExTest {

    @Test
    fun testTitle() {
        val frame = SimpleEx("Test Title")
        assertEquals("Test Title", frame.title)
    }
}