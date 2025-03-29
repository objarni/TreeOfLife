package TreeOfLife.Data

fun homes(): Category {
    return Category("Homes", homePeriods())
}

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

fun educations(): Category {
    return categoryParser(educationCategory)!!
}

const val educationCategory = """---Educations---
Rödebo förskola: Aug 1986-Jun 1987
Röstånga skola: Aug 1987-Jun 1992
Svalövs högstadieskola: Aug 1992-Jun 1995
Klippan gymnasieskola: Aug 1995-Jun 1998
Göteborgs universitet: Aug 1998-Jun 2005"""

fun employments(): Category {
    return categoryParser(employmentCategory)!!
}

const val employmentCategory = """---Employments---
Armens Musikkår Norrland: Jan-Oct 1999
AstraZeneca R&D Lund: Jun-Aug 2000
Volvo Cars: Jun-Aug 2001
Volvo Cars: Jun-Aug 2002
Volvo Cars: Jun-Aug 2003
Cadcraft: Jun 2005-Jun 2006
IGEMS: Jun 2006-Sep 2011
Lorensberg: Sep 2011-Jun 2014
Avalon Innovation: Jun 2014-Dec 2015
Vizendo: Jan 2016-Sep 2018
Objarni Productions: Sep 2018-Jan 2020
ProAgile: Mar 2020-Jun 2023
PowerFactors: Aug 2023-Jul 2024
Walley: Aug 2024-Aug 2027"""

fun relationsships(): Category {
    return categoryParser(relationshipCategory)!!
}

const val relationshipCategory = """---Love relationships---
Emma: Jan 1997-Aug 2000
Mozhdeh: Nov 2000-Sep 2013
Evelyn - 1: Oct 2013-Sep 2018
Evelyn - 2: May 2019-Jan 2020
Golnaz: May 2020-Sep 2020
Evelyn - 3: Nov 2021-Jan 2022
Josefin: Mar 2022-Jan 2025
"""

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

fun computers(): Category {
    return categoryParser(computerCategory)!!
}

const val computerCategory = """---Computers---
Commodore 64: Jan 1986-May 1992
Commodore Amiga 500: Feb 1992-Jun 1995
Compaq PC (486): Jun 1995-Jun 1998
Self-built Small tower PC (Pentium 133): Jun 1998-Jun 2005
Self-built Fulltower PC (AMD Athlon 64): Feb 2007-Jun 2011
Acer Travelmate: Oct 2004-Jun 2008
Packard Bell iMedia: Jul 2006-Jun 2010
"""

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
        TimePoint(Year(2007), Month.Companion.FEBRUARY),
        TimePoint(Year(2011), Month.Companion.JUNE),
        "Self-built Fulltower PC (AMD Athlon 64)"
    ),
    Period(
        TimePoint(Year(2004), Month.Companion.OCTOBER),
        TimePoint(Year(2008), Month.Companion.JUNE),
        "Acer Travelmate"
    ),
    Period(
        TimePoint(Year(2006), Month.Companion.JULY),
        TimePoint(Year(2010), Month.Companion.JUNE),
        "Packard Bell iMedia"
    ),
)

fun programming(): Category {
    return Category("Programming languages", programmingLanguages())
}

fun programmingLanguages(): List<Period> = listOf(
    Period(
        TimePoint(Year(1986), Month.Companion.JANUARY),
        TimePoint(Year(1992), Month.Companion.MAY),
        "BASIC"
    ),
    Period(
        TimePoint(Year(1992), Month.Companion.JULY),
        TimePoint(Year(1995), Month.Companion.JULY),
        "AMOS Professional"
    ),
    Period(
        TimePoint(Year(1995), Month.Companion.JULY),
        TimePoint(Year(2000), Month.Companion.JUNE),
        "C"
    ),
    Period(
        TimePoint(Year(1996), Month.Companion.SEPTEMBER),
        TimePoint(Year(1997), Month.Companion.MAY),
        "Turbo Pascal"
    ),
    Period(
        TimePoint(Year(2000), Month.Companion.JUNE),
        TimePoint(Year(2005), Month.Companion.JUNE),
        "C++"
    ),
    Period(
        TimePoint(Year(2005), Month.Companion.JUNE),
        TimePoint(Year(2008), Month.Companion.JUNE),
        "C#"
    ),
)
