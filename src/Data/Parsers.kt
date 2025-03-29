package TreeOfLife.Data


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

fun periodParser(string: String): Period? {
    val regex = """(.+):\s*([A-Z][a-z]{2})\s*(\d{4})\s*-\s*([A-Z][a-z]{2})\s*(\d{4})""".toRegex()
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
            TimePoint(Year(startYear.toInt()), parsedMonthStart!!),
            TimePoint(Year(endYear.toInt()), parsedMonthEnd!!),
            periodName
        )
    }
    return null
}