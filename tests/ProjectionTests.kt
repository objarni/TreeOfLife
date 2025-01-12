import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

class ProjectTests {

    @Test
    fun testProjection1() {
        val frame = ViewportProjector(
            centerEye = Point(0, 0),
            windowSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = frame.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(100, 50), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }
}

class ViewportProjector(var centerEye: Point, var windowSize: Dimension, var zoom: Double = 1.0) {
    fun projectRectangle(bottomLeft: Point, size: Dimension): Rectangle {
        return Rectangle(Point(100, 50), size)
    }
}


/*
Projection tests.

when the center eye is looking at world coordinate (0, 0) and the zoom level is 1,
   and the window size is 200x100,
   a 10x10 box should be drawn from (100, 50) to (110, 60).

when the center eye is looking at world coordinate (0, 0) and the zoom level is 2,
   and the window size is 200x100,
   a 10x10 box should be drawn from (100, 50) to (120, 70).

when the center eye is looking at world coordinate (10, 0) and the zoom level is 1,
   and the window size is 200x100,
   a 10x10 box should be drawn from (90, 50) to (100, 60).

 */