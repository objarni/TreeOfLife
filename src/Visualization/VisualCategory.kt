package TreeOfLife.Visualization

import TreeOfLife.Data.Category
import TreeOfLife.Data.educations
import TreeOfLife.Data.employments
import TreeOfLife.Data.homes
import TreeOfLife.Data.relationsships
import java.awt.Color

data class VisualCategory(val category: Category, val color: Color, val baseY: Int)

fun visualCategories(): List<VisualCategory> {
    return listOf(
        VisualCategory(homes(), Color.ORANGE, 2),
        VisualCategory(educations(), Color.BLUE, 4),
        VisualCategory(employments(), Color.DARK_GRAY, 6),
        VisualCategory(relationsships(), Color.PINK, 8)
    )
}