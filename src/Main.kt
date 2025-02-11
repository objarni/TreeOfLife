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

        pack()
    }
}

private fun createAndShowGUI() {
    val frame = MainFrame("Tree of life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}
