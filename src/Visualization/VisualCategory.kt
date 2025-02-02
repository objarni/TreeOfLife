package TreeOfLife.Visualization

import TreeOfLife.Data.Category
import java.awt.Color

data class VisualCategory(val category: Category, val color: Color, val baseY: Int)

public fun visualCategories(): List<VisualCategory> {
    return listOf(
        VisualCategory(Category("Home", TreeOfLife.Data.homePeriods()), Color.ORANGE, 2),
        VisualCategory(Category("Educations", TreeOfLife.Data.educationPeriods()), Color.BLUE, 4),
        VisualCategory(Category("Employments", TreeOfLife.Data.employmentPeriods()), Color.GRAY, 6),
        VisualCategory(Category("Love relationships", TreeOfLife.Data.loveRelationsShipPeriods()), Color.PINK, 8)
    )
}