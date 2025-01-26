# TimelinePanel

The timeline panel knows about years and months. A month is
a "unit" in its model. To fit into the TreeOfLife app as a whole,
it needs to know where Origo is, as a year and month (aka timepoint).

Apart from visualizing Periods (a concept which is outside the
timeline panel, part of the more general / simpler TreeOfLife domain),
represented by text blocks, it needs to visualize the timeline itself,
represented by an X-axis and a Y-axis.

In practical terms, it therefore needs APIs to do these things:

 - set the origo timepoint
 - set the text blocks
