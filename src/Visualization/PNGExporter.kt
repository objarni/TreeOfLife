package treeOfLife.Visualization

import treeOfLife.data.Month
import treeOfLife.data.TimePoint
import treeOfLife.data.Year
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class PNGExporter(
    private val blocks: List<TextBlock>,
    private val birthMonth: TimePoint,
    private val title: String
) {
    // Create a very wide image to capture the entire timeline
    private val imageWidth = 8000 // Double the width for higher resolution
    private val imageHeight = 2400 // Double the height for higher resolution
    private val margin = 100 // Increased margin for larger image
    private val zoom = 8.0 // Double the zoom level for larger image
    
    fun exportToPNG(filePath: String) {
        // Create buffered image
        val image = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB)
        val g2d = image.createGraphics()
        
        // Enable anti-aliasing for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
        
        // Fill background with white
        g2d.color = Color.WHITE
        g2d.fillRect(0, 0, imageWidth, imageHeight)
        
        // Draw title
        drawTitle(g2d)
        
        // Create projector for the wide timeline
        val projector = createPNGProjector()
        
        // Draw timeline elements
        drawTimelineElements(g2d, projector)
        
        // Draw age labels
        drawAgeLabels(g2d, projector)
        
        // Clean up
        g2d.dispose()
        
        // Save to file
        val outputFile = File(filePath)
        ImageIO.write(image, "PNG", outputFile)
    }
    
    private fun createPNGProjector(): ViewportProjector {
        // Center the timeline to show from birth to ~50 years old
        val centerEyeWorld = Point(300, 0) // Show more of the timeline
        val viewportSize = Dimension(imageWidth - 2 * margin, imageHeight - 300) // Leave more space for title in larger image
        return ViewportProjector(
            centerEyeWorld = centerEyeWorld,
            viewportSize = viewportSize,
            zoom = zoom
        )
    }
    
    private fun drawTitle(g2d: Graphics2D) {
        // Draw main title with larger font for bigger image
        g2d.color = Color.BLACK
        g2d.font = Font("Arial", Font.BOLD, 64)
        val titleMetrics = g2d.fontMetrics
        val titleWidth = titleMetrics.stringWidth(title)
        val titleX = (imageWidth - titleWidth) / 2
        g2d.drawString(title, titleX, 100)
        
        // Draw subtitle with birth information
        g2d.font = Font("Arial", Font.PLAIN, 36)
        val subtitleText = "Born ${birthMonth.month.name().substring(0, 3)} ${birthMonth.year.value}"
        val subtitleMetrics = g2d.fontMetrics
        val subtitleWidth = subtitleMetrics.stringWidth(subtitleText)
        val subtitleX = (imageWidth - subtitleWidth) / 2
        g2d.drawString(subtitleText, subtitleX, 160)
    }
    
    private fun drawTimelineElements(g2d: Graphics2D, projector: ViewportProjector) {
        // Draw axis
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
            )
        )
        
        // Draw all blocks
        for (block in axisBlocks + blocks) {
            val rect = projector.projectRectangle(block.rect.location, block.rect.size)
            
            // Adjust coordinates for title space
            val adjustedRect = Rectangle(
                rect.x + margin,
                rect.y + 240, // Leave more space for larger title
                rect.width,
                rect.height
            )
            
            // Draw filled rectangle
            g2d.color = block.color
            g2d.fillRect(adjustedRect.x, adjustedRect.y, adjustedRect.width, adjustedRect.height)
            
            // Draw border
            g2d.color = Color.GRAY
            g2d.drawRect(adjustedRect.x, adjustedRect.y, adjustedRect.width, adjustedRect.height)
            
            // Draw text if present
            if (block.text.isNotEmpty()) {
                g2d.color = block.color.contrastColor()
                g2d.font = Font("Arial", Font.PLAIN, (zoom * 2).toInt()) // Adjust font size for larger image
                g2d.drawString(
                    block.text,
                    adjustedRect.x + zoom.toInt(),
                    adjustedRect.y + adjustedRect.height / 2 + g2d.fontMetrics.height / 4
                )
            }
        }
    }
    
    private fun drawAgeLabels(g2d: Graphics2D, projector: ViewportProjector) {
        val ages = listOf(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50) // More age markers
        val templates = listOf(
            "Born %s, %d",
            "5 years %s %d",
            "10 years %s %d",
            "15 years %s %d",
            "20 years %s %d",
            "25 years %s %d",
            "30 years %s %d",
            "35 years %s %d",
            "40 years %s %d",
            "45 years %s %d",
            "50 years %s %d"
        )
        
        g2d.color = Color.BLACK
        g2d.font = Font("Arial", Font.BOLD, (zoom * 2).toInt()) // Adjust font size for larger image
        
        for (i in ages.indices) {
            val age = ages[i]
            val template = templates[i]
            val origo = projector.projectPoint(Point(age * 12, 0))
            val origoText = String.format(template, birthMonth.month.name().substring(0, 3), birthMonth.year.value + age)
            
            val x = origo.x + margin
            val y = origo.y + 240 + (4 * zoom).toInt() // Adjust for larger title space
            
            // Draw text
            g2d.drawString(origoText, x, y)
            
            // Draw tick mark
            g2d.stroke = BasicStroke(4f) // Thicker stroke for larger image
            g2d.drawLine(x, origo.y + 240, x, origo.y + 240 + (3 * zoom).toInt())
        }
    }
    
}