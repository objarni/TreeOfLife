package TreeOfLife

import TreeOfLife.Data.Month
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import TreeOfLife.Data.textBlocksForPeriods
import TreeOfLife.GraphicsPanel.TimelinePanel
import TreeOfLife.Visualization.visualCategories
import java.awt.BorderLayout
import java.awt.EventQueue
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

        val allBlocks2 = visualCategories().flatMap { visualCategory ->
            textBlocksForPeriods(
                visualCategory.category.periods,
                baseY = visualCategory.baseY,
                color = visualCategory.color,
                birthMonth = birthMonth
            )
        }
        timeLinePanel.setBlocks(allBlocks2)

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
