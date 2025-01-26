package TreeOfLife.GraphicsPanel

import TreeOfLife.Data.Month
import TreeOfLife.Data.TimePoint
import TreeOfLife.Data.Year
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Point
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import javax.swing.JPanel

class TimelinePanel : JPanel(), MouseWheelListener, KeyListener {
    private var zoom = 1.0
    private var centerEyeWorld = Point(0, 0)
    private val blocks = mutableListOf<TextBlock>()
    private var origoTimePoint = TimePoint(Year(1979), Month.JULY)

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

        val axisBlocks = listOf(
            TextBlock(
                rect = Rectangle(0, 0, 2000, 0),
                color = Color.RED,
                text = ""
            ),
            TextBlock(
                rect = Rectangle(0, 0, 0, 20),
                color = Color.GREEN,
                text = ""
            ),
        )

        drawYearLabel(projector, 0, "Born %s, %d", g2d)
        drawYearLabel(projector, 10, "%s, %d (10 years)", g2d)
        drawYearLabel(projector, 18, "%s, %d (18 years)", g2d)
        drawYearLabel(projector, 25, "%s, %d (25 years)", g2d)

        for (box in axisBlocks + blocks) {
            val rect = projector.projectRectangle(box.rect.location, box.rect.size)
            g2d.color = box.color
            g2d.fillRect(rect.x, rect.y, rect.width, rect.height)
            g2d.color = Color.GRAY
            g2d.drawRect(rect.x, rect.y, rect.width, rect.height)
            g2d.color = Color.BLACK
            g2d.drawString(box.text, rect.x, rect.y)
        }

        g2d.dispose() // Clean up the graphics object
    }

    private fun drawYearLabel(
        projector: ViewportProjector,
        age: Int,
        template: String,
        g2d: Graphics
    ) {
        val origo = projector.projectPoint(Point(age * 12, 0))
        val origoText = String.format(template, origoTimePoint.month.name(), origoTimePoint.year.value + age)
        g2d.drawString(origoText, origo.x, origo.y + 12)
        g2d.drawLine(origo.x, origo.y, origo.x, origo.y + 5)
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

    fun setBlocks(blocks: List<TextBlock>) {
        this.blocks.clear()
        this.blocks.addAll(blocks)
        repaint()
    }

    fun setOrigoTimePoint(timePoint: TimePoint) {
        origoTimePoint = timePoint
        repaint()
    }
}