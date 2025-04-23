package TreeOfLife.Data

// Common regex patterns
private const val MONTH_PATTERN = """[A-Z][a-z]{2}"""
private const val YEAR_PATTERN = """\d{4}"""
private const val TIME_POINT_PATTERN = """($MONTH_PATTERN)\s*($YEAR_PATTERN)"""
private const val PERIOD_NAME_PATTERN = """.+"""

// Helper function to create a TimePoint from month and year strings
private fun createTimePoint(monthStr: String, yearStr: String): TimePoint? {
    val parsedMonth = monthParser(monthStr) ?: return null
    return TimePoint(Year(yearStr.toInt()), parsedMonth)
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
    val presentRegex = """\s*($PERIOD_NAME_PATTERN):\s*$TIME_POINT_PATTERN\s*-\s*$""".toRegex()
    val presentMatch = presentRegex.find(string)
    if (presentMatch != null) {
        val (periodName, startMonth, startYear) = presentMatch.destructured
        val startTimePoint = createTimePoint(startMonth, startYear) ?: return null
        return Period(startTimePoint, currentTimePoint, periodName)
    }

    // Try parsing regular format (e.g. "Göteborg: Aug 2024-Jul 2025")
    val regex = """\s*($PERIOD_NAME_PATTERN):\s*$TIME_POINT_PATTERN\s*-\s*$TIME_POINT_PATTERN""".toRegex()
    val matchResult = regex.find(string)
    if (matchResult != null) {
        val (periodName, startMonth, startYear, endMonth, endYear) = matchResult.destructured
        val startTimePoint = createTimePoint(startMonth, startYear) ?: return null
        val endTimePoint = createTimePoint(endMonth, endYear) ?: return null
        return Period(startTimePoint, endTimePoint, periodName)
    }
    return null
}

fun periodParserShortFormat(string: String): Period? {
    val regex = """\s*($PERIOD_NAME_PATTERN):\s*($MONTH_PATTERN)\s*-\s*($MONTH_PATTERN)\s*($YEAR_PATTERN)""".toRegex()
    val matchResult = regex.find(string)
    if (matchResult != null) {
        val (periodName, startMonth, endMonth, yearString) = matchResult.destructured
        val startTimePoint = createTimePoint(startMonth, yearString) ?: return null
        val endTimePoint = createTimePoint(endMonth, yearString) ?: return null
        return Period(startTimePoint, endTimePoint, periodName)
    }
    return null
}

fun timePointParser(string: String): TimePoint? {
    val regex = """\s*$TIME_POINT_PATTERN""".toRegex()
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

data class TreeOfLifeData(
    val birthMonth: TimePoint,
    val categories: List<Category>,
    val name: String
)

fun findValueForPrefix(
    lines: List<String>,
    prefix: String
): String? {
    val line = lines.firstOrNull { it.trim().startsWith(prefix) }
    return line?.removePrefix(prefix)?.trim()
}

fun treeOfLifeParser(input: String, currentTimePoint: TimePoint): TreeOfLifeData {
    val lines = input.split("\n")
    val birthMonthLine = findValueForPrefix(lines, "Birth month:")
    if (birthMonthLine == null) {
        throw IllegalArgumentException("Birth month not found in the file")
    }
    val birthTimePoint = timePointParser(birthMonthLine)
    if (birthTimePoint == null) {
        throw IllegalArgumentException("Invalid birth month format")
    }
    val nameValue = findValueForPrefix(lines, "Name:")
    val name = nameValue ?: "Timeline"
    val categoriesString = lines.dropWhile { !it.trim().startsWith("---") }.joinToString("\n")
    val categories = categoriesParser(categoriesString, currentTimePoint)
    return TreeOfLifeData(birthTimePoint, categories, name)
}