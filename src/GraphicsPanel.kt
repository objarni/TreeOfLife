package TreeOfLife

import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import javax.swing.JPanel

data class Box(
    val rect: Rectangle,
    val color: Color
) {
    companion object
}

class GraphicsPanel : JPanel(), MouseWheelListener, KeyListener {
    private var zoom = 1.0
    private var centerEyeWorld = Point(0, 0)
    private val boxes = mutableListOf<Box>()

    fun SetBoxes(boxes: List<Box>) {
        this.boxes.clear()
        this.boxes.addAll(boxes)
        repaint()
    }

    init {
        // Set preferred size for the panel
        preferredSize = Dimension(400, 300)
        background = Color.WHITE
        addMouseWheelListener(this)
        addKeyListener(this)
        isFocusable = true
        requestFocusInWindow()
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        zoom += e.wheelRotation * 0.1

        if (zoom < 0.1)
            zoom = 0.1
        if (zoom > 10)
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
            centerEyeWorld = centerEyeWorld,
            viewportSize = Dimension(width, height),
            zoom = zoom
        )

        for (box in boxes) {
            g2d.color = box.color
            val rect = projector.projectRectangle(box.rect.location, box.rect.size)
            g2d.drawRect(rect.x, rect.y, rect.width, rect.height)
        }

        g2d.dispose() // Clean up the graphics object
    }

    override fun keyTyped(e: KeyEvent?) {}

    var onEscapePressed: () -> Unit = {}

    override fun keyPressed(e: KeyEvent?) {
        if (e == null)
            return
        when (e.keyCode) {
            KeyEvent.VK_LEFT -> centerEyeWorld.translate(-10, 0)
            KeyEvent.VK_RIGHT -> centerEyeWorld.translate(10, 0)
            KeyEvent.VK_UP -> centerEyeWorld.translate(0, 10)
            KeyEvent.VK_DOWN -> centerEyeWorld.translate(0, -10)
            KeyEvent.VK_ESCAPE -> onEscapePressed()
        }
        repaint()
    }

    override fun keyReleased(e: KeyEvent?) {}
}