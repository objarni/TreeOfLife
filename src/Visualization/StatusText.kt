package treeOfLife.Visualization

import treeOfLife.data.TimePoint

object StatusText {
    fun calculate(timePoint: TimePoint, birthMonth: TimePoint, overlappingPeriods: List<String>): String {
        val age = calculateAge(timePoint, birthMonth)
        val ageText = if (age > 0) " (age $age)" else ""
        
        return if (overlappingPeriods.isEmpty()) {
            "${timePoint.month.name()}, ${timePoint.year.value}$ageText"
        } else {
            "${timePoint.month.name()}, ${timePoint.year.value}$ageText - ${overlappingPeriods.joinToString(", ")}"
        }
    }
    
    private fun calculateAge(current: TimePoint, birth: TimePoint): Int {
        val yearDiff = current.year.value - birth.year.value
        val monthDiff = current.month.value - birth.month.value
        
        return if (monthDiff < 0) {
            yearDiff - 1
        } else {
            yearDiff
        }
    }
}