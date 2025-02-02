package TreeOfLife

import TreeOfLife.Data.Month
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import TreeOfLife.Data.educationPeriods
import TreeOfLife.Data.employmentPeriods
import TreeOfLife.Data.homePeriods
import TreeOfLife.Data.loveRelationsShipPeriods
import TreeOfLife.Data.textBlocksForPeriods
import TreeOfLife.GraphicsPanel.TimelinePanel
import TreeOfLife.Visualization.visualCategories
import java.awt.Color
import java.awt.EventQueue
import javax.swing.JFrame

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

        contentPane.add(timeLinePanel) // Add RectanglePanel to the content pane
        pack() // Adjust frame size to match panel's preferred size

    }
}

data class Text(val positionInTime: TimePoint, val levelAboveTimeline: Int, val text: String)

private fun createAndShowGUI() {
    val frame = MainFrame("Tree of life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}
