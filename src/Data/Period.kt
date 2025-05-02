package treeOfLife.data

import treeOfLife.Visualization.TextBlock
import java.awt.Color
import java.awt.Rectangle

data class Period(val start: TimePoint, val end: TimePoint, val text: String) {
    fun toBlock(color: Color, monthOfBirth: TimePoint, y: Int): TextBlock {
        val startYear = start.year.value
        val startMonth = start.month.value
        val endYear = end.year.value
        val endMonth = end.month.value
        val birthYear = monthOfBirth.year.value
        val birthMonth = monthOfBirth.month.value
        val xStart = (startYear - birthYear) * 12 + startMonth - birthMonth
        val xEnd = (endYear - birthYear) * 12 + endMonth - birthMonth
        return TextBlock(
            rect = Rectangle(xStart, y, xEnd - xStart, 2),
            color = color,
            text = text
        )
    }

    init {
        require(start.year.value <= end.year.value) { "Start year must be before or equal to end year" }
        require(start.year.value != end.year.value || start.month.value <= end.month.value) { "Start month must be before or equal to end month" }
    }
}

fun textBlocksForPeriods(periods: List<Period>, baseY: Int, color: Color, birthMonth: TimePoint): List<TextBlock> {
    /* algorithm. keep track of all added-so-far blocks. for each period, check if it overlaps with any of the added blocks. if it does, increment y. */
    val addedBlocks = mutableListOf<TextBlock>()
    return periods.map { period ->
        var y = baseY
        while (addedBlocks.any { it.rect.intersects(period.toBlock(color, birthMonth, y).rect) }) {
            y ++
        }
        val block = period.toBlock(color, birthMonth, y)
        addedBlocks.add(block)
        block
    }
}