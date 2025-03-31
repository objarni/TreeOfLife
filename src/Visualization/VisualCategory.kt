package TreeOfLife.Visualization

import TreeOfLife.Data.Category
import TreeOfLife.Data.Month
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import TreeOfLife.Data.categories
import TreeOfLife.Data.computers
import TreeOfLife.Data.educations
import TreeOfLife.Data.employments
import TreeOfLife.Data.homes
import TreeOfLife.Data.programming
import TreeOfLife.Data.relationsships
import TreeOfLife.Data.textBlocksForPeriods
import java.awt.Color

data class VisualCategory(val category: Category, val color: Color, val baseY: Int)

fun visualCategories(): List<VisualCategory> {
    // For now, compute y values using the textBlocksForPeriods algorithm
    var initialY = 0
    val allCategories = categories()
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
