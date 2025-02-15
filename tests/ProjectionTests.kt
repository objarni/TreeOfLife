import TreeOfLife.GraphicsPanel.ViewportProjector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Dimension
import java.awt.Point

class ProjectionTests {

    @Test
    fun testProjectionWithInitialParameters() {
        val projector = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = projector.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(100, 40), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }

    @Test
    fun testProjectionUserPannedEast10Units() {
        val project = ViewportProjector(
            centerEyeWorld = Point(10, 0),
            viewportSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = project.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(90, 40), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }

    @Test
    fun testProjectionUserPannedNorth10Units() {
        val projector = ViewportProjector(
            centerEyeWorld = Point(0, 10),
            viewportSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = projector.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(100, 50), box.location)
        assertEquals(Dimension(10, 10), box.size)
    }

    @Test
    fun testProjectionUserZoomIn() {
        val frame = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(200, 100),
        )
        frame.zoom = 2.0
        val box = frame.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(100, 30), box.location)
        assertEquals(Dimension(20, 20), box.size)
    }

    @Test
    fun testProjectionUserZoomInAndPan() {
        val frame = ViewportProjector(
            centerEyeWorld = Point(10, 10),
            viewportSize = Dimension(200, 100),
        )
        frame.zoom = 2.0
        val box = frame.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(80, 50), box.location)
        assertEquals(Dimension(20, 20), box.size)
    }

    @Test
    fun testProjectionYAxis() {
        val projector = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(400, 400),
            zoom = 1.0
        )
        val box = projector.projectRectangle(Point(0, 0), Dimension(2, 20))
        assertEquals(Point(200, 200-20), box.location)
        assertEquals(Dimension(2, 20), box.size)
    }

    @Test
    fun testProjectionOfBoxAtXStart10() {
        val frame = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(200, 100),
        )
        frame.zoom = 1.0
        val box = frame.projectRectangle(Point(10, 0), Dimension(10, 10))
        assertEquals(Point(110, 40), box.location)
    }

    @Test
    fun testProjectionOfAPoint() {
        val frame = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(200, 100),
        )
        frame.zoom = 1.0
        val point = frame.projectPoint(Point(10, 0))
        assertEquals(Point(110, 50), point)
    }

    @Test
    fun testViewportToWorldProjectionOfWindowCenterPoint() {
        val frame = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(200, 100),
        )
        frame.zoom = 1.0
        val point = frame.reverseProjectPoint(Point(100, 50))
        assertEquals(Point(0, 0), point)
    }

    @Test
    fun testViewportToWorldProjectionOfTopLeftPoint() {
        val frame = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(200, 100),
        )
        frame.zoom = 1.0
        val point = frame.reverseProjectPoint(Point(0, 0))
        assertEquals(Point(-100, 50), point)
    }

    @Test
    fun testViewportToWorldProjectionOfTopLeftPointZoomed() {
        val frame = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(200, 100),
        )
        frame.zoom = 2.0
        val point = frame.reverseProjectPoint(Point(0, 0))
        assertEquals(Point(-50, 25), point)
    }
}
