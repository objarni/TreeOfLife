package treeOfLife.Data

data class Month(val value: Int) {

    fun name(): String {
        return java.time.Month.of(value).name
    }

    init {
        require(value in 1..12) { "Month value must be between 1 and 12" }
    }

    companion object {
        val JANUARY = Month(1)
        val FEBRUARY = Month(2)
        val MARCH = Month(3)
        val APRIL = Month(4)
        val MAY = Month(5)
        val JUNE = Month(6)
        val JULY = Month(7)
        val AUGUST = Month(8)
        val SEPTEMBER = Month(9)
        val OCTOBER = Month(10)
        val NOVEMBER = Month(11)
        val DECEMBER = Month(12)
    }
}