package TreeOfLife

import TreeOfLife.Data.*
import TreeOfLife.GraphicsPanel.TextBlock
import TreeOfLife.GraphicsPanel.TimelinePanel
import TreeOfLife.Visualization.visualCategories
import java.awt.BorderLayout
import java.awt.EventQueue
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.io.File
import java.io.IOException
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.KeyStroke

class MainFrame(title: String) : JFrame() {
    private lateinit var timeLinePanel: TimelinePanel
    private var lastModified: Long = 0
    private val dataFilePath = getDocumentsPath() + "/TreeOfLife.txt"
    private val fileCheckTimer = javax.swing.Timer(1000) { checkFileAndReload() } // 1 second interval

    init {
        createUI(title)
        startFileWatcher()
    }

    private fun startFileWatcher() {
        lastModified = File(dataFilePath).lastModified()
        fileCheckTimer.start()
    }

    private fun checkFileAndReload() {
        val currentModified = File(dataFilePath).lastModified()
        if (currentModified > lastModified) {
            println("File changed, reloading data...") // Debug print
            lastModified = currentModified
            reloadData()
        }
    }

    private fun reloadData() {
        try {
            val data = loadDataFile(dataFilePath)
            val birthMonth = data.first

            val allBlocks = visualCategories().flatMap { visualCategory ->
                textBlocksForPeriods(
                    visualCategory.category.periods,
                    baseY = visualCategory.baseY,
                    color = visualCategory.color,
                    birthMonth = birthMonth
                )
            }
            val labelBlocks = visualCategories().map { visualCategory ->
                TextBlock(
                    rect = Rectangle(-20, visualCategory.baseY, 1, 1),
                    color = visualCategory.color,
                    text = visualCategory.category.category
                )
            }
            timeLinePanel.setOrigoTimePoint(birthMonth)
            timeLinePanel.setBlocks(allBlocks + labelBlocks)
            println("Data reloaded successfully") // Debug print
        } catch (e: Exception) {
            println("Error reloading data: ${e.message}") // Debug print
            popupMessageDialog("Error reloading data file: ${e.message}")
        }
    }

    private fun createUI(title: String) {
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1000, 800)
        setLocationRelativeTo(null)
        timeLinePanel = TimelinePanel()
        timeLinePanel.onEscapePressed = { dispose() }

        val dataFilePath = getDocumentsPath() + "/TreeOfLife.txt"
        // if no data file exists, create a default one
        if (!File(dataFilePath).exists()) {
            writeDefaultFile(dataFilePath)
        }
        val data: Pair<TimePoint, List<Category>>
        try {
            data = loadDataFile(dataFilePath)
        }
        catch (e: Exception) {
            popupMessageDialog("Error loading data file: ${e.message}")
            throw e
        }

        val birthMonth = data.first

        timeLinePanel.setOrigoTimePoint(birthMonth)

        val lifeTime = visualCategories().map { it.category }
        for (y in 1979..2025) {
            val year = Year(y)
            val month = Month.JANUARY
            val timePoint = TimePoint(year, month)
            val overlappingPeriods = overlappingPeriods(timePoint, lifeTime)
            println("Year: $year, overlappingPeriods: $overlappingPeriods")
        }

        val allBlocks = visualCategories().flatMap { visualCategory ->
            textBlocksForPeriods(
                visualCategory.category.periods,
                baseY = visualCategory.baseY,
                color = visualCategory.color,
                birthMonth = birthMonth
            )
        }
        val labelBlocks = visualCategories().map { visualCategory ->
            TextBlock(
                rect = Rectangle(-20, visualCategory.baseY, 1, 1),
                color = visualCategory.color,
                text = visualCategory.category.category
            )
        }
        timeLinePanel.setBlocks(allBlocks + labelBlocks)

        layout = BorderLayout()
        val bottomPanel = JPanel()
        bottomPanel.add(JButton("Button 1"))
        bottomPanel.add(JTextField("Text field 1"))

        contentPane.add(timeLinePanel, BorderLayout.CENTER)
        contentPane.add(bottomPanel, BorderLayout.SOUTH)

        createMenuBar()

        pack()
    }

    private fun writeDefaultFile(dataFilePath: String) {
        val defaultData = """
Birth month: Jan 1983

---Hem---
Stockholm: Sep 1983-Jun 1984
Malmö: Jun 1984-Jun 2000

###

---Utbildningar---
Möllevångsskolan: Aug 1989-Jun 1995
JENSEN: Aug 1995-Jun 2000
Lärarprogrammet LTH: Aug 2000-Jun 2005

###

---Arbete---
Lärare: Aug 2005-Jun 2010
Programmerare: Aug 2010-Jun 2020

###

---Hobbyer---
Måla: Jan 1984-May 2025
Cykel: Jan 1995-Jun 2000
            """.trimIndent()
        File(dataFilePath).writeText(defaultData)
    }

    private fun popupMessageDialog(msg: String) {
        JOptionPane.showMessageDialog(
            this,
            msg,
            "Error",
            JOptionPane.ERROR_MESSAGE
        )
    }

    private fun openDataFile() {
        openTextFile("${System.getProperty("user.home")}/Documents/TreeOfLife.txt")
    }

    fun openTextFile(file: String) {
        val os = System.getProperty("os.name").lowercase()

        try {
            when {
                os.contains("win") -> {
                    // Windows: Use Notepad
                    ProcessBuilder("notepad.exe", file).start()
                }

                os.contains("mac") -> {
                    // macOS: Try CotEditor or VS Code, fallback to open -e
                    val editorsToTry = listOf(
                        "/Applications/CotEditor.app" to listOf("open", "-a", "CotEditor",
                            file),
                        "/Applications/Visual Studio Code.app" to listOf("open", "-a", "Visual Studio Code",
                            file),
                        null to listOf("open", "-e",
                            file) // fallback to TextEdit in plaintext mode
                    )

                    for ((appPath, command) in editorsToTry) {
                        if (appPath == null || File(appPath).exists()) {
                            ProcessBuilder(command).start()
                            break
                        }
                    }
                }

                else -> {
                    println("Unsupported OS: $os")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun createMenuBar() {
        val menuBar = JMenuBar()

        val fileMenu = JMenu("File")
        val aboutMenuItem = JMenuItem("About")
        val openDataFileMenuItem = JMenuItem("Open Data File")
        val quitMenuItem = JMenuItem("Quit")

        aboutMenuItem.addActionListener {
            JOptionPane.showMessageDialog(
                this,
                "Tree of Life - visualize your life\nVersion 0.1",
                "About",
                JOptionPane.INFORMATION_MESSAGE
            )
        }

        openDataFileMenuItem.addActionListener {
            openDataFile()
        }

        quitMenuItem.addActionListener {
            dispose()
        }

        quitMenuItem.accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0)

        fileMenu.add(aboutMenuItem)
        fileMenu.add(openDataFileMenuItem)
        fileMenu.addSeparator()
        fileMenu.add(quitMenuItem)

        menuBar.add(fileMenu)
        jMenuBar = menuBar
    }

    override fun dispose() {
        fileCheckTimer.stop()
        super.dispose()
    }
}

private fun createAndShowGUI() {
    val frame = MainFrame("Tree of life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}



//Year and month of birth