package TreeOfLife.GraphicsPanel

import java.awt.Color
import java.awt.Rectangle

data class TextBlock(
    val rect: Rectangle,
    val color: Color,
    val text: String
) {
    companion object
}