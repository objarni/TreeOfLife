package TreeOfLife.Data

public fun homes(): Category {
    return Category("Home", homePeriods())
}

public fun educations(): Category {
    return Category("Educations", educationPeriods())
}

public fun employments(): Category {
    return Category("Employments", employmentPeriods())
}

public fun loveRelationsShips(): Category {
    return Category("Love relationships", loveRelationsShipPeriods())
}