import treeOfLife.data.Category
import treeOfLife.data.TimePoint
import treeOfLife.data.categoryParser

const val homeCategory: String = """
 ---Homes---
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

fun educations(currentTimePoint: TimePoint): Category {
    return categoryParser(educationCategory, currentTimePoint)!!
}

const val educationCategory = """
    ---Educations---
Rödebo förskola: Aug 1986-Jun 1987
Röstånga skola: Aug 1987-Jun 1992
Svalövs högstadieskola: Aug 1992-Jun 1995
Klippan gymnasieskola: Aug 1995-Jun 1998
Göteborgs universitet: Aug 1998-Jun 2005"""



