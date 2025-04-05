import org.junit.jupiter.api.Test

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

