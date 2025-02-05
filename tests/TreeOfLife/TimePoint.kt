package TreeOfLife

import TreeOfLife.Data.TimePoint

internal fun chronological(before: TimePoint, after: TimePoint): Boolean {
    if (before.year.value == after.year.value) {
        return before.month.value < after.month.value
    }
    return before.year.value < after.year.value
}