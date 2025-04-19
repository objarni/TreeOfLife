package TreeOfLife.Visualization

import TreeOfLife.Data.TimePoint

object StatusText {
    fun calculate(timePoint: TimePoint, overlappingPeriods: List<String>): String {
        return if (overlappingPeriods.isEmpty()) {
            "${timePoint.month.name()}, ${timePoint.year.value}"
        } else {
            "${timePoint.month.name()}, ${timePoint.year.value} - ${overlappingPeriods.joinToString(", ")}"
        }
    }
}