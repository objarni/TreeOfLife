package TreeOfLife.Domain

import TreeOfLife.GraphicsPanel.Box
import java.awt.Color
import java.awt.Rectangle

data class Year(val value: Int) {
    init {
        require(value in 1900..2100) { "Year value must be between 1900 and 2020" }
    }
}

data class Month(val value: Int) {
    init {
        require(value in 1..12) { "Month value must be between 1 and 12" }
    }

    companion object {
        val JANUARY = Month(1)
        val FEBRUARY = Month(2)
        val MARCH = Month(3)
        val APRIL = Month(4)
        val MAY = Month(5)
        val JUNE = Month(6)
        val JULY = Month(7)
        val AUGUST = Month(8)
        val SEPTEMBER = Month(9)
        val OCTOBER = Month(10)
        val NOVEMBER = Month(11)
        val DECEMBER = Month(12)
    }
}

data class TimePoint(val year: Year, val month: Month)

data class Period(val start: TimePoint, val end: TimePoint, val text: String) {
    fun toBox(color: Color, monthOfBirth: TimePoint, y: Int): Box {
        val startYear = start.year.value
        val startMonth = start.month.value
        val endYear = end.year.value
        val endMonth = end.month.value
        val birthYear = monthOfBirth.year.value
        val birthMonth = monthOfBirth.month.value
        val xStart = (startYear - birthYear) * 12 + startMonth - birthMonth
        val xEnd = (endYear - birthYear) * 12 + endMonth - birthMonth
        return Box(
            rect = Rectangle(xStart, y, xEnd - xStart, 1),
            color = color,
            text = text
        )
    }

    init {
        require(start.year.value <= end.year.value) { "Start year must be before or equal to end year" }
        require(start.year.value != end.year.value || start.month.value <= end.month.value) { "Start month must be before or equal to end month" }
    }
}

fun boxesFromPeriods(periods: List<Period>, baseY: Int, color: Color, birthMonth: TimePoint): List<Box> {
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