
import java.awt.EventQueue
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
        contentPane.add(TimeLinePanel()) // Add RectanglePanel to the content pane
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
