package TreeOfLife.Data


fun topLevelParser(string: String): Pair<TimePoint, List<Category>> {
    val lines = string.split("\n")
    val prefix = "Birth month:"
    val birthTimePointLine = lines.firstOrNull { it.trim().startsWith(prefix) }
    val birthTimePoint = if (birthTimePointLine != null) {
        timePointParser(birthTimePointLine.removePrefix(prefix).trim())
    } else {
        throw IllegalArgumentException("Birth month not found in the file")
        null
    }
    val categoriesString = lines.dropWhile { !it.trim().startsWith("---") }.joinToString("\n")
    val categories = categoriesParser(categoriesString)
    return Pair(birthTimePoint!!, categories)
}

fun categoriesParser(string: String): List<Category> {
    val categories = mutableListOf<Category>()
    val categoryStrings = string.split("###").map { it.trim() }
    for (categoryString in categoryStrings) {
        if (categoryString.isNotBlank()) {
            val category = categoryParser(categoryString)
            if (category == null)
                throw IllegalArgumentException("Invalid category string: $categoryString")
            categories.add(category)
        }
    }
    return categories
}

fun categoryParser(string: String): Category? {
    val lines = string.split("\n")
    val categoryName = lines[0].trim().removePrefix("---").removeSuffix("---").trim()
    val periods = lines.drop(1).mapNotNull { periodParser(it) }
    return Category(categoryName, periods)
}

fun periodParser(string: String): Period? {
    val periodShort = periodParserShortFormat(string)
    if (periodShort != null)
        return periodShort
    val regex = """\s*(.+):\s*([A-Z][a-z]{2})\s*(\d{4})\s*-\s*([A-Z][a-z]{2})\s*(\d{4})""".toRegex()
    val matchResult = regex.find(string)
    if (matchResult != null) {
        val (periodName, startMonth, startYear, endMonth, endYear) = matchResult.destructured
        val parsedMonthStart = monthParser(startMonth)
        if (parsedMonthStart == null)
            return null
        val parsedMonthEnd = monthParser(endMonth)
        if (parsedMonthEnd == null)
            return null
        return Period(
            TimePoint(Year(startYear.toInt()), parsedMonthStart),
            TimePoint(Year(endYear.toInt()), parsedMonthEnd),
            periodName
        )
    }
    return null
}

fun periodParserShortFormat(string: String): Period? {
    val regex = """\s*(.+):\s*([A-Z][a-z]{2})\s*-\s*([A-Z][a-z]{2})\s*(\d{4})""".toRegex()
    val matchResult = regex.find(string)
    if (matchResult != null) {
        val (periodName, startMonth, endMonth, yearString) = matchResult.destructured
        val year = yearString.toInt()
        val parsedMonthStart = monthParser(startMonth)
        if (parsedMonthStart == null)
            return null
        val parsedMonthEnd = monthParser(endMonth)
        if (parsedMonthEnd == null)
            return null
        return Period(
            TimePoint(Year(year), parsedMonthStart),
            TimePoint(Year(year), parsedMonthEnd),
            periodName
        )
    }
    return null
}

fun timePointParser(string: String): TimePoint? {
    val regex = """\s*([A-Z][a-z]{2})\s*(\d{4})""".toRegex()
    val matchResult = regex.find(string)
    if (matchResult != null) {
        val (month, year) = matchResult.destructured
        val parsedMonth = monthParser(month)
        if (parsedMonth == null)
            return null
        return TimePoint(Year(year.toInt()), parsedMonth)
    }
    return null
}


fun monthParser(string: String): Month? {
    val month = string.lowercase()
    if (month.startsWith("jan")) return Month.JANUARY
    if (month.startsWith("feb")) return Month.FEBRUARY
    if (month.startsWith("mar")) return Month.MARCH
    if (month.startsWith("apr")) return Month.APRIL
    if (month.startsWith("may")) return Month.MAY
    if (month.startsWith("jun")) return Month.JUNE
    if (month.startsWith("jul")) return Month.JULY
    if (month.startsWith("aug")) return Month.AUGUST
    if (month.startsWith("sep")) return Month.SEPTEMBER
    if (month.startsWith("oct")) return Month.OCTOBER
    if (month.startsWith("nov")) return Month.NOVEMBER
    if (month.startsWith("dec")) return Month.DECEMBER
    return null
}
