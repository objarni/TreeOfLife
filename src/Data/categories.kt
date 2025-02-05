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