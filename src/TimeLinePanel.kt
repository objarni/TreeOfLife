package TreeOfLife

import java.awt.*
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import javax.swing.JPanel

class TimeLinePanel : JPanel(), MouseWheelListener {
    // Declare a member variable that keeps track of zoom level
    private var zoom = 1.0


    init {
        // Set preferred size for the panel
        preferredSize = Dimension(400, 300)
        background = Color.WHITE
        addMouseWheelListener(this)
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        zoom += e.wheelRotation * 0.1

        if(zoom < 0.1)
            zoom = 0.1
        if(zoom > 10)
            zoom = 10.0

        repaint()
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g) // Call the superclass's method to ensure proper rendering

        // Cast Graphics to Graphics2D for more features if needed
        val g2d = g.create() // Create a copy of the Graphics object

        // Shorten zoom to 2 decimal places
        val zoomStr = String.format("%.2f", zoom)
        g2d.drawString("Zoom: $zoomStr", 10, 20)

        val projector = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(width, height),
            zoom = zoom
        )

        g2d.color = Color.RED
        val xaxis = projector.projectRectangle(Point(0, 0), Dimension(20, 1))
        g2d.drawRect(xaxis.x, xaxis.y, xaxis.width, xaxis.height)

        g2d.color = Color.GREEN
        val yaxis = projector.projectRectangle(Point(0, 0), Dimension(1, 20))
        g2d.drawRect(yaxis.x, yaxis.y, yaxis.width, yaxis.height)

        g2d.dispose() // Clean up the graphics object
    }
}