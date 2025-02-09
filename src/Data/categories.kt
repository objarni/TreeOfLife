package TreeOfLife.Data

fun homes(): Category {
    return Category("Home", homePeriods())
}

fun educations(): Category {
    return Category("Educations", educationPeriods())
}

fun employments(): Category {
    return Category("Employments", employmentPeriods())
}

fun relationsships(): Category {
    return Category("Love relationships", relationshipPeriods())
}

fun computers(): Category {
    return Category("Computers", computerPeriods())
}

fun educationPeriods(): List<Period> = listOf(
    Period(
        TimePoint(Year(1986), Month.Companion.AUGUST),
        TimePoint(Year(1987), Month.Companion.JUNE),
        "Rödebo förskola"
    ),
    Period(
        TimePoint(Year(1987), Month.Companion.AUGUST),
        TimePoint(Year(1995), Month.Companion.JUNE),
        "Röstånga skola"
    ),
    Period(
        TimePoint(Year(1995), Month.Companion.AUGUST),
        TimePoint(Year(1998), Month.Companion.JUNE),
        "Klippan gymnasieskola"
    ),
    Period(
        TimePoint(Year(1998), Month.Companion.AUGUST),
        TimePoint(Year(2005), Month.Companion.JUNE),
        "Göteborgs universitet"
    ),
)

fun employmentPeriods(): List<Period> = listOf(
    Period(
        TimePoint(Year(1999), Month.Companion.JANUARY),
        TimePoint(Year(1999), Month.Companion.OCTOBER),
        "Armens Musikkår Norrland"
    ),
    Period(
        TimePoint(Year(2000), Month.Companion.JUNE),
        TimePoint(Year(2000), Month.Companion.AUGUST),
        "AstraZeneca R&D Lund"
    ),
    Period(
        TimePoint(Year(2001), Month.Companion.JUNE),
        TimePoint(Year(2001), Month.Companion.AUGUST),
        "Volvo Cars"
    ),
    Period(
        TimePoint(Year(2002), Month.Companion.JUNE),
        TimePoint(Year(2002), Month.Companion.AUGUST),
        "Volvo Cars"
    ),
    Period(
        TimePoint(Year(2003), Month.Companion.JUNE),
        TimePoint(Year(2003), Month.Companion.AUGUST),
        "Volvo Cars"
    ),
    Period(
        TimePoint(Year(2005), Month.Companion.JUNE),
        TimePoint(Year(2006), Month.Companion.JUNE),
        "Cadcraft"
    ),
    Period(
        TimePoint(Year(2006), Month.Companion.JUNE),
        TimePoint(Year(2011), Month.Companion.SEPTEMBER),
        "IGEMS"
    ),
    Period(
        TimePoint(Year(2011), Month.Companion.SEPTEMBER),
        TimePoint(Year(2014), Month.Companion.JUNE),
        "Lorensberg"
    ),
    Period(
        TimePoint(Year(2014), Month.Companion.JUNE),
        TimePoint(Year(2015), Month.Companion.DECEMBER),
        "Avalon Innovation"
    ),
    Period(
        TimePoint(Year(2016), Month.Companion.JANUARY),
        TimePoint(Year(2018), Month.Companion.SEPTEMBER),
        "Vizendo"
    ),
    Period(
        TimePoint(Year(2018), Month.Companion.SEPTEMBER),
        TimePoint(Year(2020), Month.Companion.JANUARY),
        "Objarni Productions"
    ),
    Period(
        TimePoint(Year(2020), Month.Companion.MARCH),
        TimePoint(Year(2023), Month.Companion.JUNE),
        "ProAgile"
    ),
    Period(
        TimePoint(Year(2023), Month.Companion.AUGUST),
        TimePoint(Year(2024), Month.Companion.JULY),
        "PowerFactors"
    ),
    Period(
        TimePoint(Year(2024), Month.Companion.AUGUST),
        TimePoint(Year(2027), Month.Companion.AUGUST),
        "Walley"
    ),
)

fun computerPeriods(): List<Period> = listOf(
    Period(
        TimePoint(Year(1986), Month.Companion.JANUARY),
        TimePoint(Year(1992), Month.Companion.MAY),
        "Commodore 64"
    ),
    Period(
        TimePoint(Year(1992), Month.Companion.FEBRUARY),
        TimePoint(Year(1995), Month.Companion.JUNE),
        "Commodore Amiga 500"
    ),
    Period(
        TimePoint(Year(1995), Month.Companion.JUNE),
        TimePoint(Year(1998), Month.Companion.JUNE),
        "Compaq PC (486)"
    ),
    Period(
        TimePoint(Year(1998), Month.Companion.JUNE),
        TimePoint(Year(2005), Month.Companion.JUNE),
        "Self-built Small tower PC (Pentium 133)"
    ),
    Period(
        TimePoint(Year(2003), Month.Companion.JUNE),
        TimePoint(Year(2006), Month.Companion.JUNE),
        "Self-built Fulltower PC (AMD Athlon 64)"
    )
)

fun homePeriods(): List<Period> = listOf(
    Period(
        TimePoint(Year(1979), Month.Companion.JULY),
        TimePoint(Year(1983), Month.Companion.JUNE),
        "Stockholm"
    ),
    Period(
        TimePoint(Year(1983), Month.Companion.JUNE),
        TimePoint(Year(1997), Month.Companion.MAY),
        "Röstånga"
    ),
    Period(
        TimePoint(Year(1997), Month.Companion.MAY),
        TimePoint(Year(1998), Month.Companion.MAY),
        "Klippan"
    ),
    Period(
        TimePoint(Year(1998), Month.Companion.MAY),
        TimePoint(Year(1999), Month.Companion.JANUARY),
        "Hisingen"
    ),
    Period(
        TimePoint(Year(1999), Month.Companion.JANUARY),
        TimePoint(Year(1999), Month.Companion.NOVEMBER),
        "Boden"
    ),
    Period(
        TimePoint(Year(1999), Month.Companion.NOVEMBER),
        TimePoint(Year(2005), Month.Companion.JUNE),
        "Hisingen"
    ),
    Period(
        TimePoint(Year(2005), Month.Companion.JUNE),
        TimePoint(Year(2008), Month.Companion.JUNE),
        "Borås"
    ),
    Period(
        TimePoint(Year(2008), Month.Companion.JUNE),
        TimePoint(Year(2011), Month.Companion.SEPTEMBER),
        "Mölndal"
    ),
    Period(
        TimePoint(Year(2011), Month.Companion.SEPTEMBER),
        TimePoint(Year(2013), Month.Companion.AUGUST),
        "Kortedala (Eric Börjessons Väg)"
    ),
    Period(
        TimePoint(Year(2013), Month.Companion.AUGUST),
        TimePoint(Year(2014), Month.Companion.JULY),
        "Hertford"
    ),
    Period(
        TimePoint(Year(2014), Month.Companion.AUGUST),
        TimePoint(Year(2014), Month.Companion.OCTOBER),
        "Kortedala (Januarigatan)"
    ),
    Period(
        TimePoint(Year(2014), Month.Companion.OCTOBER),
        TimePoint(Year(2015), Month.Companion.AUGUST),
        "Kortedala (Decembergatan)"
    ),
    Period(
        TimePoint(Year(2015), Month.Companion.SEPTEMBER),
        TimePoint(Year(2015), Month.Companion.DECEMBER),
        "Hisingen (Kvillebäcken)"
    ),
    Period(
        TimePoint(Year(2016), Month.Companion.JANUARY),
        TimePoint(Year(2016), Month.Companion.DECEMBER),
        "Kortedala (Advensvägen)"
    ),
    Period(
        TimePoint(Year(2017), Month.Companion.JANUARY),
        TimePoint(Year(2023), Month.Companion.JUNE),
        "Kortedala (Fastlagsgatan)"
    ),
    Period(
        TimePoint(Year(2023), Month.Companion.JUNE),
        TimePoint(Year(2025), Month.Companion.MAY),
        "Tangovägen"
    ),
)

fun relationshipPeriods(): List<Period> {
    return listOf(
        Period(
            TimePoint(Year(1997), Month.Companion.JANUARY),
            TimePoint(Year(2000), Month.Companion.AUGUST),
            "Emma"
        ),
        Period(
            TimePoint(Year(2000), Month.Companion.NOVEMBER),
            TimePoint(Year(2013), Month.Companion.SEPTEMBER),
            "Mozhdeh"
        ),
        Period(
            TimePoint(Year(2013), Month.Companion.OCTOBER),
            TimePoint(Year(2018), Month.Companion.SEPTEMBER),
            "Evelyn - 1"
        ),
        Period(
            TimePoint(Year(2019), Month.Companion.MAY),
            TimePoint(Year(2020), Month.Companion.JANUARY),
            "Evelyn - 2"
        ),
        Period(
            TimePoint(Year(2020), Month.Companion.MAY),
            TimePoint(Year(2020), Month.Companion.SEPTEMBER),
            "Golnaz"
        ),
        Period(
            TimePoint(Year(2021), Month.Companion.NOVEMBER),
            TimePoint(Year(2022), Month.Companion.JANUARY),
            "Evelyn - 3"
        ),
        Period(
            TimePoint(Year(2022), Month.Companion.MARCH),
            TimePoint(Year(2025), Month.Companion.JANUARY),
            "Josefin"
        )
    )
}