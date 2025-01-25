package TreeOfLife

import java.awt.Color
import java.awt.Rectangle

data class Box(
    val rect: Rectangle,
    val color: Color,
    val text: String
) {
    companion object
}

fun fromPeriods(periods: List<Period>, baseY: Int, color: Color, birthMonth: TimePoint): List<Box> {
    /* algorithm. keep track of all added-so-far boxes. for each period, check if it overlaps with any of the added boxes. if it does, increment y. */
    var addedBoxes = mutableListOf<Box>()
    return periods.map { period ->
        var y = baseY
        while (addedBoxes.any { it.rect.intersects(period.toBox(color, birthMonth, y).rect) }) {
            y++
        }
        val box = period.toBox(color, birthMonth, y)
        addedBoxes.add(box)
        box
    }
}