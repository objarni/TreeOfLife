package TreeOfLife.Data

fun homes(): Category {
    return categoryParser(homeCategory)!!
}

const val homeCategory: String = """---Homes---
Röstånga: Jul 1983-Jul 1997
Klippan: Jun 1996-Jul 1997
Hisingen: Jun 1997-May 1998
Boden: May 1998-Nov 1999
Hisingen: Nov 1999-Jun 2005
Borås: Jun 2005-Jun 2008
Mölndal: Jun 2008-Sep 2011
Kortedala (Eric Börjessons Väg): Sep 2011-Aug 2013
Hertford: Aug 2013-Jul 2014
Kortedala (Januarigatan): Aug 2014-Oct 2014
Kortedala (Decembergatan): Oct 2014-Aug 2015
Hisingen (Kvillebäcken): Sep 2015-Dec 2015
Kortedala (Adventsvägen): Jan 2016-Dec 2016
Kortedala (Fastlagsgatan): Jan 2017-Jun 2023
Tangovägen: Jun 2023-May 2025
"""

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

fun programming(): Category {
    return categoryParser(programmingCategory)!!
}

const val programmingCategory = """---Programming languages---
BASIC: Jan 1986-May 1992
AMOS Professional: Jul 1992-Jul 1995
C: Jul 1995-Jun 2000
Turbo Pascal: Sep 1996-May 1997
C++: Jun 2000-Jun 2005
C#: Jun 2005-Jun 2008
"""

