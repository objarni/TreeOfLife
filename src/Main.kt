package TreeOfLife

import TreeOfLife.Data.*
import TreeOfLife.GraphicsPanel.TextBlock
import TreeOfLife.GraphicsPanel.TimelinePanel
import TreeOfLife.Visualization.visualCategories
import java.awt.BorderLayout
import java.awt.EventQueue
import java.awt.Rectangle
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JTextField

class MainFrame(title: String) : JFrame() {

    init {
        createUI(title)
    }

    private fun createUI(title: String) {
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1000, 800)
        setLocationRelativeTo(null)
        val timeLinePanel = TimelinePanel()
        timeLinePanel.onEscapePressed = { dispose() }

        val birthMonth = TimePoint(Year(1979), Month.JULY)

        val lifeTime = visualCategories().map { it.category }
        for (year in 1979..2025) {
            val year = Year(year)
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

        contentPane.add(timeLinePanel, BorderLayout.CENTER) // Add RectanglePanel to the content pane
        contentPane.add(bottomPanel, BorderLayout.SOUTH)

        createMenuBar()

        pack()
    }

    private fun createMenuBar() {
        val menuBar = JMenuBar()

        val fileMenu = JMenu("File")
        val aboutMenuItem = JMenuItem("About")
        val helpMenuItem = JMenuItem("Help")
        val quitMenuItem = JMenuItem("Quit")

        aboutMenuItem.addActionListener {
            JOptionPane.showMessageDialog(
                this,
                "Tree of Life Application\nVersion 1.0",
                "About",
                JOptionPane.INFORMATION_MESSAGE
            )
        }

        helpMenuItem.addActionListener {
            JOptionPane.showMessageDialog(this, "Help content goes here.", "Help", JOptionPane.INFORMATION_MESSAGE)
        }

        quitMenuItem.addActionListener {
            dispose()
        }

        fileMenu.add(aboutMenuItem)
        fileMenu.add(helpMenuItem)
        fileMenu.addSeparator()
        fileMenu.add(quitMenuItem)

        menuBar.add(fileMenu)
        jMenuBar = menuBar
    }
}

private fun createAndShowGUI() {
    val frame = MainFrame("Tree of life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}
