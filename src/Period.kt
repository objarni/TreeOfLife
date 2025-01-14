package TreeOfLife

import java.awt.Color
import java.awt.Rectangle

data class Year(val value: Int) {
    init {
        require(value in 1900..2020) { "Year value must be between 1900 and 2020" }
    }
}

data class Month(val value: Int) {
    init {
        require(value in 1..12) { "Month value must be between 1 and 12" }
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
            rect = Rectangle(xStart, y, xEnd - xStart, y + 1),
            color = color
        )
    }

    init {
        require(start.year.value <= end.year.value) { "Start year must be before or equal to end year" }
        require(start.year.value != end.year.value || start.month.value <= end.month.value) { "Start month must be before or equal to end month" }
    }
}