package treeOfLife.Visualization

import java.awt.Color

fun Color.contrastColor(): Color
{
    val r = this.red
    val g = this.green
    val b = this.blue

    val luminance = 0.299 * r + 0.587 * g + 0.114 * b

    return when {
        luminance> 186 -> {
            Color(0, 0, 0) // Return black for light colors
        }
        else -> {
            Color(255, 255, 255) // Return white for dark colors
        }
    }
}