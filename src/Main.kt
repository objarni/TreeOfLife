
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
    }
}

private fun createAndShowGUI() {

    val frame = SimpleEx("Tree of life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}


//
//fun main() {
//    val name = "Kotlin"
//    println("Hello, $name!")
//
//    for (i in 1..5) {
//        println("i = $i")
//    }
//}