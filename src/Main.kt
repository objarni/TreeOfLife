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

        // Periods in different homes in my life
        val homes = homePeriods()

        val axisBoxes = listOf(
                Box(
                    rect = Rectangle(0, 0, 2000, 1),
                    color = Color.RED,
                    text = "*"
                ),
                Box(
                    rect = Rectangle(0, 0, 1, 20),
                    color = Color.GREEN,
                    text = ""
                ),
        )

        val homeBoxes = Box.fromPeriods(homes, color=Color.BLUE, baseY=4, birthMonth=TimePoint(Year(1979), Month.JULY))

        val allBoxes = axisBoxes + homeBoxes
        gfxPanel.SetBoxes(allBoxes)

        contentPane.add(gfxPanel) // Add RectanglePanel to the content pane
        pack() // Adjust frame size to match panel's preferred size

    }
}

fun homePeriods(): List<Period> = listOf(
    Period(
        TimePoint(Year(1979), Month.JULY),
        TimePoint(Year(1983), Month.JUNE),
        "Stockholm"
    ),
    Period(
        TimePoint(Year(1983), Month.JUNE),
        TimePoint(Year(1997), Month.MAY),
        "Röstånga"
    ),
    Period(
        TimePoint(Year(1997), Month.MAY),
        TimePoint(Year(1998), Month.MAY),
        "Klippan"
    ),
    Period(
        TimePoint(Year(1998), Month.MAY),
        TimePoint(Year(1999), Month.JANUARY),
        "Hisingen"
    ),
    Period(
        TimePoint(Year(1999), Month.JANUARY),
        TimePoint(Year(1999), Month.NOVEMBER),
        "Boden"
    ),
)

private fun createAndShowGUI() {
    val frame = MainFrame("Tree of life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}
