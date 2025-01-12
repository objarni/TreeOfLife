import TreeOfLife.ViewportProjector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Dimension
import java.awt.Point

class ProjectTests {

    @Test
    fun testProjectionWithInitialParameters() {
        val projector = ViewportProjector(
            centerEyeWorld = Point(0, 0),
            viewportSize = Dimension(200, 100),
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
            viewportSize = Dimension(200, 100),
            zoom = 1.0
        )
        val box = project.projectRectangle(Point(0, 0), Dimension(10, 10))
        assertEquals(Point(90, 50), box.location)
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
        assertEquals(Point(100, 60), box.location)
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
        assertEquals(Point(100, 50), box.location)
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
        assertEquals(Point(90, 60), box.location)
        assertEquals(Dimension(20, 20), box.size)
    }
}
