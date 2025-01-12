import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

class ProjectTests {

    @Test
    fun testProjectionWithInitialParameters() {
        val projector = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            windowSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = projector.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(100, 50), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }

    @Test
    fun testProjectionUserPannedEast10Units() {
        val project = ViewportProjector(
            centerEyeWorld = Point(10, 0),
            windowSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = project.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(90, 50), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }

//    @Test
    fun testProjectionUserPannedNorth10Units() {
        val projector = ViewportProjector(
            centerEyeWorld = Point(0, 10),
            windowSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = projector.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(100, 60), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }

    @Test
    fun testProjectionUserZoomIn() {
        val frame = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            windowSize = Dimension(200, 100),
        )
        frame.zoom = 2.0
        val box = frame.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(100, 50), box.location)
        assertEquals(Dimension(20, 20), box.size)
    }
}

class ViewportProjector(var centerEyeWorld: Point, var windowSize: Dimension, var zoom: Double = 1.0) {
    fun projectRectangle(bottomLeftWorldCoordinate: Point, sizeWorldCoords: Dimension): Rectangle {
        val yFlippedWorldCoordinate = Point(bottomLeftWorldCoordinate.x, -bottomLeftWorldCoordinate.y)
        val viewportCoordinate = yFlippedWorldCoordinate - centerEyeWorld + windowSize / 2
        var viewportDimension = sizeWorldCoords * zoom
        return Rectangle(viewportCoordinate, viewportDimension)
    }
}

private operator fun Dimension.times(zoom: Double): Dimension {
    return Dimension((width * zoom).toInt(), (height * zoom).toInt())
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