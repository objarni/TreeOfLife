# TimelinePanel

The timeline panel knows about years and months. A month is
a "unit" in its model. To fit into the Tree of life app as a whole,
it needs to know where Origo is, as a year and month (aka timepoint).

Apart from visualizing Periods (a concept which is outside the
timeline panel, part of the more general / simpler Tree of life domain),
represented by text blocks, it needs to visualize the timeline itself,
represented by an X-axis and a Y-axis.

In practical terms, it therefore needs APIs to do these things:

 - set the origo timepoint
 - set the text blocks


# The cursor

The cursor is a visual element that indicates the "current timepoint",
which is the timepoint that the user selected.

When clicking on the timeline, the window coordinates are converted
to world coordinates, and the X coordinate is used to set the cursor
position.

Given an X position, we can calculate the age of the visualized person
at that point in time, as well as the year and month. We can also
compute the "cross-section" of the periods active at that time:

  crossSection: XCoord -> BirthMonth -> (Age, Year, Month, Periods)
