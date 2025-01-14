package TreeOfLife

import java.awt.Color
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.Point
import java.awt.Rectangle
import javax.swing.JFrame

class SimpleEx(title: String) : JFrame() {

    init {
        createUI(title)
    }

    private fun createUI(title: String) {
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 300)
        setLocationRelativeTo(null)
        val gfxPanel = GraphicsPanel()
        gfxPanel.SetBoxes(listOf(
            GraphicsPanel.Box(
                rect = Rectangle(100, 100, 50, 50),
                color = Color.BLUE
            ),
            GraphicsPanel.Box(
                rect = Rectangle(0, 0, 20, 1),
                color = Color.RED
            ),
            GraphicsPanel.Box(
                rect = Rectangle(0, 0, 1, 20),
                color = Color.GREEN
            )
        ))

        contentPane.add(gfxPanel) // Add RectanglePanel to the content pane
        pack() // Adjust frame size to match panel's preferred size

    }
}

private fun createAndShowGUI() {
    val frame = SimpleEx("Tree of life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}
