package treeOfLife.Data

data class Year(val value: Int) {
    init {
        require(value in 1900..2100) { "Year value must be between 1900 and 2020" }
    }
}