package treeOfLife.data

import java.nio.file.Paths

fun getDocumentsPath(): String {
    val userHome = System.getProperty("user.home")
    val documentsPath = when {
        System.getProperty("os.name").contains("Windows", ignoreCase = true) -> Paths.get(userHome, "Documents")
        else -> Paths.get(userHome, "Documents")
    }
    return documentsPath.toString()
}

fun loadTreeOfLifeDataFile(dataFilePath: String, currentTimePoint: TimePoint): TreeOfLifeData {
    val fileContent = readFile(dataFilePath)
    return treeOfLifeParser(fileContent, currentTimePoint)
}

fun readFile(string: String):String {
    val file = java.io.File(string)
    return file.readText()
}
