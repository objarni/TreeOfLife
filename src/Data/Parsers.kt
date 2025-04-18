package TreeOfLife.Data

// Helper function to create a TimePoint from month and year strings
private fun createTimePoint(monthStr: String, yearStr: String): TimePoint? {
    val parsedMonth = monthParser(monthStr) ?: return null
    return TimePoint(Year(yearStr.toInt()), parsedMonth)
}

// Helper function to parse a period with given start and end time points
private fun createPeriod(name: String, start: TimePoint, end: TimePoint): Period {
    return Period(start, end, name)
}

fun topLevelParser(string: String, currentTimePoint: TimePoint): Pair<TimePoint, List<Category>> {
    val lines = string.split("\n")
    val prefix = "Birth month:"
    val birthTimePointLine = lines.firstOrNull { it.trim().startsWith(prefix) }
    val birthTimePoint = if (birthTimePointLine != null) {
        timePointParser(birthTimePointLine.removePrefix(prefix).trim())
    } else {
        throw IllegalArgumentException("Birth month not found in the file")
    }
    val categoriesString = lines.dropWhile { !it.trim().startsWith("---") }.joinToString("\n")
    val categories = categoriesParser(categoriesString, currentTimePoint)
    return Pair(birthTimePoint!!, categories)
}

fun categoriesParser(string: String, currentTimePoint: TimePoint): List<Category> {
    val categories = mutableListOf<Category>()
    val categoryStrings = string.split("###").map { it.trim() }
    for (categoryString in categoryStrings) {
        if (categoryString.isNotBlank()) {
            val category = categoryParser(categoryString, currentTimePoint)
            if (category == null)
                throw IllegalArgumentException("Invalid category string: $categoryString")
            categories.add(category)
        }
    }
    return categories
}

fun categoryParser(string: String, currentTimePoint: TimePoint): Category? {
    val lines = string.split("\n")
    val categoryName = lines[0].trim().removePrefix("---").removeSuffix("---").trim()
    val periods = lines.drop(1).mapNotNull { periodParser(it, currentTimePoint) }
    return Category(categoryName, periods)
}

fun periodParser(string: String, currentTimePoint: TimePoint): Period? {
    val periodShort = periodParserShortFormat(string)
    if (periodShort != null)
        return periodShort

    // Try parsing present format first (e.g. "Göteborg: Aug 2024-")
    val presentRegex = """\s*(.+):\s*([A-Z][a-z]{2})\s*(\d{4})\s*-\s*$""".toRegex()
    val presentMatch = presentRegex.find(string)
    if (presentMatch != null) {
        val (periodName, startMonth, startYear) = presentMatch.destructured
        val startTimePoint = createTimePoint(startMonth, startYear) ?: return null
        return createPeriod(periodName, startTimePoint, currentTimePoint)
    }

    // Try parsing regular format (e.g. "Göteborg: Aug 2024-Jul 2025")
    val regex = """\s*(.+):\s*([A-Z][a-z]{2})\s*(\d{4})\s*-\s*([A-Z][a-z]{2})\s*(\d{4})""".toRegex()
    val matchResult = regex.find(string)
    if (matchResult != null) {
        val (periodName, startMonth, startYear, endMonth, endYear) = matchResult.destructured
        val startTimePoint = createTimePoint(startMonth, startYear) ?: return null
        val endTimePoint = createTimePoint(endMonth, endYear) ?: return null
        return createPeriod(periodName, startTimePoint, endTimePoint)
    }
    return null
}

fun periodParserShortFormat(string: String): Period? {
    val regex = """\s*(.+):\s*([A-Z][a-z]{2})\s*-\s*([A-Z][a-z]{2})\s*(\d{4})""".toRegex()
    val matchResult = regex.find(string)
    if (matchResult != null) {
        val (periodName, startMonth, endMonth, yearString) = matchResult.destructured
        val startTimePoint = createTimePoint(startMonth, yearString) ?: return null
        val endTimePoint = createTimePoint(endMonth, yearString) ?: return null
        return createPeriod(periodName, startTimePoint, endTimePoint)
    }
    return null
}

fun timePointParser(string: String): TimePoint? {
    val regex = """\s*([A-Z][a-z]{2})\s*(\d{4})""".toRegex()
    val matchResult = regex.find(string)
    if (matchResult != null) {
        val (month, year) = matchResult.destructured
        return createTimePoint(month, year)
    }
    return null
}

fun monthParser(string: String): Month? {
    val month = string.lowercase()
    return when {
        month.startsWith("jan") -> Month.JANUARY
        month.startsWith("feb") -> Month.FEBRUARY
        month.startsWith("mar") -> Month.MARCH
        month.startsWith("apr") -> Month.APRIL
        month.startsWith("may") -> Month.MAY
        month.startsWith("jun") -> Month.JUNE
        month.startsWith("jul") -> Month.JULY
        month.startsWith("aug") -> Month.AUGUST
        month.startsWith("sep") -> Month.SEPTEMBER
        month.startsWith("oct") -> Month.OCTOBER
        month.startsWith("nov") -> Month.NOVEMBER
        month.startsWith("dec") -> Month.DECEMBER
        else -> null
    }
}
