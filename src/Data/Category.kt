package treeOfLife.data

data class Category(val category: String, val periods: List<Period>)

fun overlappingPeriods(
    point: TimePoint,
    categories: List<Category>
): List<String> {
    return categories.flatMap { category ->
        category.periods.filter { period ->
            chronological(period.start, point) && chronological(point, period.end)
        }.map { it.text }
    }
}