package treeOfLife.Visualization

import treeOfLife.data.Category
import treeOfLife.data.Month
import treeOfLife.data.TimePoint
import treeOfLife.data.Year
import treeOfLife.data.categories
import treeOfLife.data.textBlocksForPeriods
import java.awt.Color

data class VisualCategory(val category: Category, val color: Color, val baseY: Int)

fun visualCategories(currentTimePoint: TimePoint): List<VisualCategory> {
    // For now, compute y values using the textBlocksForPeriods algorithm
    var initialY = 0
    val allCategories = categories(currentTimePoint)
    val allColors = listOf(
        Color.ORANGE,
        Color.BLUE,
        Color.DARK_GRAY,
        Color.PINK,
        Color.GREEN,
        Color.RED
    )
    return allCategories.zip(allColors).map { (category, color) ->
        val textBlocks = textBlocksForPeriods(category.periods, 0, color, TimePoint(Year(2000), Month.JANUARY))
        val maxY = textBlocks.maxOfOrNull { it.rect.y + it.rect.height } ?: 0
        println("Category ${category.category} has maxY $maxY")
        val visualCategory = VisualCategory(category, color, initialY)
        initialY += maxY + 2
        visualCategory
    }
}
