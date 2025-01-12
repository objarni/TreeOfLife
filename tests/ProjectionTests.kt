import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

class ProjectTests {

    @Test
    fun testProjectionWithInitialParameters() {
        val frame = ViewportProjector(
            centerEye = Point(0, 0),
            windowSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = frame.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(100, 50), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }

    @Test
    fun testProjectionUserPannedRight10Units() {
        val frame = ViewportProjector(
            centerEye = Point(10, 0),
            windowSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = frame.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(90, 50), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }
}

class ViewportProjector(var centerEye: Point, var windowSize: Dimension, var zoom: Double = 1.0) {
    fun projectRectangle(bottomLeft: Point, size: Dimension): Rectangle {
        val location = bottomLeft - centerEye + windowSize / 2
        return Rectangle(location, size)
    }
}

private operator fun Dimension.div(divider: Int): Point {
    return Point(width / divider, height / divider)
}

private operator fun Point.plus(pt: Point): Point {
    return Point(x + pt.x, y + pt.y)
}

private operator fun Point.unaryMinus(): Point {
    return Point(-x, -y)
}

private operator fun Point.minus(pt: Point): Point {
    return Point(x - pt.x, y - pt.y)
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