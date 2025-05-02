import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import treeOfLife.Visualization.contrastColor
import java.awt.Color

class ContrastingColorTests {
    @Test
    fun `check that white is the contrast to black`() {
        val black = Color(0, 0, 0)
        val white = Color(255, 255, 255)
        assertEquals(white, black.contrastColor())
    }

    @Test
    fun `check that black is the contrast to white`() {
        val black = Color(0, 0, 0)
        val white = Color(255, 255, 255)
        assertEquals(black, white.contrastColor())
    }

    @Test
    fun `check that white is the contrast to red`() {
        assertEquals(Color.white, Color.RED.contrastColor())
    }

    @Test
    fun `check that white is the contrast to green`() {
        assertEquals(Color.WHITE, Color.GREEN.contrastColor())
    }

    @Test
    fun `check that black is the contrast to almost white`() {
        assertEquals(Color.BLACK, Color(220, 220, 220).contrastColor())
    }
}

