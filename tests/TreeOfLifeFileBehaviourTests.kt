import TreeOfLife.Data.Month
import TreeOfLife.Data.Period
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import TreeOfLife.Data.textBlocksForPeriods
import TreeOfLife.Data.homes
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color

interface TreeOfLifeTextFileOperations {
    fun readWholeFile(): String?
    fun writeDefaultFile()
}

class TreeOfLifeFileBehaviourTests {
    @Test
    fun testIfNoFileExistsTheDefaultIsWritten() {
        var fileWritten = false
        val fileOperations: TreeOfLifeTextFileOperations = object : TreeOfLifeTextFileOperations {
            override fun readWholeFile(): String? {
                return null
            }

            override fun writeDefaultFile() {
                fileWritten = true
            }
        }

        val content = LoadTreeOfLifeFile(fileOperations)
    }

    private fun LoadTreeOfLifeFile(operations: TreeOfLifeTextFileOperations): String {
        operations.writeDefaultFile()
        return ""
    }
}

