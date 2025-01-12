import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

class TimeLinePanel : JPanel() {
    init {
        // Set preferred size for the panel
        preferredSize = Dimension(400, 300)
        background = Color.WHITE
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g) // Call the superclass's method to ensure proper rendering

        // Cast Graphics to Graphics2D for more features if needed
        val g2d = g.create() // Create a copy of the Graphics object

        // Set color for the rectangle
        g2d.color = Color.BLUE

        // Draw a rectangle at (50, 50) with width 200 and height 100
        g2d.drawRect(50, 50, 200, 100)

        // Optionally fill the rectangle
        g2d.color = Color.CYAN
        g2d.fillRect(50, 50, 200, 100)

        g2d.dispose() // Clean up the graphics object
    }
}