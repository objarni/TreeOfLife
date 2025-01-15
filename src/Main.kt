package TreeOfLife

import java.awt.Color
import java.awt.EventQueue
import java.awt.Rectangle
import javax.swing.JFrame

class MainFrame(title: String) : JFrame() {

    init {
        createUI(title)
    }

    private fun createUI(title: String) {
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 300)
        setLocationRelativeTo(null)
        val gfxPanel = GraphicsPanel()
        gfxPanel.onEscapePressed = { dispose() }
        gfxPanel.SetBoxes(
            listOf(
                Box(
                    rect = Rectangle(0, 0, 2000, 0),
                    color = Color.RED
                ),
                Box(
                    rect = Rectangle(0, 0, 0, 20),
                    color = Color.GREEN
                ),
                Period(
                    TimePoint(Year(1979), Month(7)),
                    TimePoint(Year(1983), Month(7)),
                    "Stockholm"
                ).toBox(
                    Color.BLUE,
                    y = 4,
                    monthOfBirth = TimePoint(
                        Year(1979), Month(7)
                    )
                )

            )
        )

        contentPane.add(gfxPanel) // Add RectanglePanel to the content pane
        pack() // Adjust frame size to match panel's preferred size

    }
}

private fun createAndShowGUI() {
    val frame = MainFrame("Tree of life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}
