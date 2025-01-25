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

        val birthMonth = TimePoint(Year(1979), Month.JULY)
        val homeBoxes = Box.fromPeriods(
            homePeriods(),
            color = Color.BLUE,
            baseY = 2,
            birthMonth = birthMonth
        )
        val educationBoxes = Box.fromPeriods(
            listOf(
                Period(
                    TimePoint(Year(1986), Month.AUGUST),
                    TimePoint(Year(1987), Month.JUNE),
                    "Rödebo förskola"
                ),
                Period(
                    TimePoint(Year(1987), Month.AUGUST),
                    TimePoint(Year(1995), Month.JUNE),
                    "Röstånga skola"
                ),
            ),
            color = Color.GREEN,
            baseY = 4,
            birthMonth = birthMonth
        )

        val allBoxes = axisBoxes + homeBoxes + educationBoxes
        gfxPanel.SetBoxes(allBoxes)
        gfxPanel.SetTexts(allTexts())

        contentPane.add(gfxPanel) // Add RectanglePanel to the content pane
        pack() // Adjust frame size to match panel's preferred size

    }
}

data class Text(val positionInTime: TimePoint, val levelAboveTimeline: Int, val text: String)

fun allTexts(): List<Text> {
    val yearLabels = listOf(
        Text(TimePoint(Year(1979), Month.JULY), 0, "1979"),
        Text(TimePoint(Year(1983), Month.JUNE), 0, "1983"),
        Text(TimePoint(Year(1987), Month.JUNE), 0, "1987"),
        Text(TimePoint(Year(1991), Month.JUNE), 0, "1991"),
        Text(TimePoint(Year(1995), Month.JUNE), 0, "1995"),
        Text(TimePoint(Year(1999), Month.JUNE), 0, "1999"),
    )
    return yearLabels
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
