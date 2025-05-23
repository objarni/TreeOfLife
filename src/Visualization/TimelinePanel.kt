package treeOfLife.Visualization

import treeOfLife.data.Month
import treeOfLife.data.TimePoint
import treeOfLife.data.Year
import java.awt.Color
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import javax.swing.JPanel
import kotlin.math.abs

class TimelinePanel() : JPanel(), MouseWheelListener, KeyListener, MouseListener, MouseMotionListener {
    private var zoom = 7.7
    private var centerEyeWorld = Point(100, 0)
    private val blocks = mutableListOf<TextBlock>()
    private var birthMonth = TimePoint(Year(1979), Month.JULY)
    private var title: String = "Timeline"
    private var cursorPosition = 10 * 12 // Default position at age 10, January (0-based month)
    var onCursorMoved: (TimePoint, TimePoint, List<String>) -> Unit = { _, _, _ -> }
    private val clickableAreaHeight = 25 // Height of area where clicking will move the cursor
    private val originalFont = font
    private var ordinaryFont: java.awt.Font = font
    private var biggerFont: java.awt.Font = ordinaryFont.deriveFont(ordinaryFont.size2D * 2)

    init {
        // Set preferred size for the panel
        preferredSize = Dimension(1000, 800)
        background = Color.WHITE
        addMouseWheelListener(this)
        addKeyListener(this)
        addMouseListener(this)
        addMouseMotionListener(this)
        isFocusable = true
        requestFocusInWindow()
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        zoom += e.wheelRotation * 0.11

        val minZoom = 0.1
        if (zoom < minZoom)
            zoom = minZoom
        val maxZoom = 20.0
        if (zoom > maxZoom)
            zoom = maxZoom

        ordinaryFont = originalFont.deriveFont(1.5f * zoom.toFloat())
//        biggerFont = ordinaryFont.deriveFont(ordinaryFont.size2D * 2)

        repaint()
    }


    override fun paintComponent(g: Graphics) {
        super.paintComponent(g) // Call the superclass's method to ensure proper rendering

        // Cast Graphics to Graphics2D for more features if needed
        val g2d = g.create() // Create a copy of the Graphics object

        // Shorten zoom to 2 decimal places
        val zoomStr = String.format("%.2f", zoom)
        g2d.drawString("Zoom: $zoomStr", 10, 20)

        // Draw the title centered
        g2d.font = biggerFont
        val fontMetrics = g2d.fontMetrics
        val titleWidth = fontMetrics.stringWidth(title)
        val titleHeight = fontMetrics.height
        val titleX = (width - titleWidth) / 2
        val titleY = titleHeight // Position it at the top with some padding
        g2d.drawString(title, titleX, titleY)
        g2d.font = ordinaryFont

        val projector = makeProjector()

        // Draw cursor
        drawCursor(projector, g2d as Graphics2D)

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
        drawYearLabel(projector, 5, "5 years %s %d", g2d)
        drawYearLabel(projector, 10, "10 years %s %d", g2d)
        drawYearLabel(projector, 18, "18 years %s %d", g2d)
        drawYearLabel(projector, 25, "25 years %s %d ", g2d)
        drawYearLabel(projector, 30, "30 years %s %d ", g2d)
        drawYearLabel(projector, 40, "40 years %s %d ", g2d)
        drawYearLabel(projector, 45, "45 years %s %d ", g2d)

        for (box in axisBlocks + blocks) {
            val rect = projector.projectRectangle(box.rect.location, box.rect.size)
            g2d.color = box.color
            g2d.fillRect(rect.x, rect.y, rect.width, rect.height)
            g2d.color = Color.GRAY
            g2d.drawRect(rect.x, rect.y, rect.width, rect.height)
            g2d.color = box.color.contrastColor()
            g2d.drawString(box.text, rect.x + zoom.toInt(), rect.y + rect.height / 2)
        }

        g2d.dispose() // Clean up the graphics object
    }

    private fun drawCursor(projector: ViewportProjector, g2d: Graphics2D) {
        val cursorX = projector.projectPoint(Point(cursorPosition, 0)).x
        val xAxisY = projector.projectPoint(Point(0, 0)).y // Get Y position of X-axis
        val cursorStartY = xAxisY + 50 // Start 50 pixels below X-axis
        g2d.color = Color.BLUE
        g2d.drawLine(cursorX, cursorStartY, cursorX, xAxisY) // Draw from below X-axis up to X-axis
        // Draw arrow pointing upwards
        val arrowSize = 10
        g2d.drawLine(cursorX, xAxisY, cursorX - arrowSize, xAxisY + arrowSize)
        g2d.drawLine(cursorX, xAxisY, cursorX + arrowSize, xAxisY + arrowSize)
        // Draw stitched line to the top
        val originalStroke = g2d.stroke
        val originalColor = g2d.color
        g2d.color = Color.LIGHT_GRAY
        g2d.stroke = java.awt.BasicStroke(1.0f, java.awt.BasicStroke.CAP_BUTT, java.awt.BasicStroke.JOIN_MITER, 10.0f, floatArrayOf(5.0f, 5.0f), 0.0f)
        g2d.drawLine(cursorX, xAxisY, cursorX, 0)
        g2d.stroke = originalStroke // Restore original stroke
        g2d.color = originalColor // Restore original color
    }

    private fun drawYearLabel(
        projector: ViewportProjector,
        age: Int,
        template: String,
        g2d: Graphics
    ) {
        val origo = projector.projectPoint(Point(age * 12, 0))
        val origoText = String.format(template, birthMonth.month.name().substring(0, 3), birthMonth.year.value + age)
        g2d.drawString(origoText, origo.x, origo.y + 3*zoom.toInt())
        g2d.drawLine(origo.x, origo.y, origo.x, origo.y + 2*zoom.toInt())
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

    fun setBirthMonth(timePoint: TimePoint) {
        this.birthMonth = timePoint
        repaint()
    }

    fun setTitle(title: String) {
        this.title = title
        repaint()
    }

    override fun mouseClicked(e: MouseEvent) {
        println("Mouse clicked at: ${e.x}, ${e.y}")
        val projector = makeProjector()

        val mouseWorldCoordinate = projector.reverseProjectPoint(Point(e.x, e.y))
        println("Mouse world coordinate: ${mouseWorldCoordinate.x}, ${mouseWorldCoordinate.y}")

        val origoWindowYCoord = projector.projectPoint(Point(0, 0)).y

        if((mouseWorldCoordinate.x < 0) ||
            (abs(e.y - origoWindowYCoord) > clickableAreaHeight)) {
            println("Click outside clickable area")
            return
        }

        cursorPosition = mouseWorldCoordinate.x
        
        // Calculate the time point and notify listeners
        val years = cursorPosition / 12
        val months = cursorPosition % 12 + 1 // Month values are 1-based
        val cursorTimePoint = TimePoint(
            Year(birthMonth.year.value + years),
            Month(months)
        )
        
        // Find overlapping periods
        val overlappingPeriods = blocks
            .filter { it.text.isNotEmpty() } // Filter out axis blocks
            .filter { block ->
                val blockStart = block.rect.x
                val blockEnd = blockStart + block.rect.width
                cursorPosition in blockStart..blockEnd
            }
            .map { it.text }
        
        onCursorMoved(cursorTimePoint, birthMonth, overlappingPeriods)
        repaint()
    }

    override fun mousePressed(e: MouseEvent?) {}
    override fun mouseReleased(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}

    override fun mouseMoved(e: MouseEvent) {
        val projector = makeProjector()
        val origoWindowCoord = projector.projectPoint(Point(0, 0))
        val xAxisY = origoWindowCoord.y
        val distanceToXAxis = abs(e.y - xAxisY)
        val mouseDeltaOrigoY = e.x - origoWindowCoord.x
        
        // Change cursor to hand if within clickable area
        cursor = if (distanceToXAxis <= clickableAreaHeight && mouseDeltaOrigoY > 0) {
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        } else {
            Cursor.getDefaultCursor()
        }
    }

    private fun makeProjector(): ViewportProjector = ViewportProjector(
        centerEyeWorld = centerEyeWorld,
        viewportSize = Dimension(width, height),
        zoom = zoom
    )

    override fun mouseDragged(e: MouseEvent) {
        // Not used but required by MouseMotionListener
    }
}