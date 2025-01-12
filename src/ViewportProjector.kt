package TreeOfLife

import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

class ViewportProjector(var centerEyeWorld: Point, var viewportSize: Dimension, var zoom: Double = 1.0) {
    fun projectRectangle(bottomLeftWorldCoordinate: Point, sizeWorldCoords: Dimension): Rectangle {
        val topLeftWorldCoordinate = bottomLeftWorldCoordinate + Point(0, sizeWorldCoords.height)
        val viewportCoordinate = viewportSize / 2 - (Point(centerEyeWorld.x, -centerEyeWorld.y) + topLeftWorldCoordinate) * zoom
        var viewportDimension = sizeWorldCoords * zoom
        return Rectangle(viewportCoordinate, viewportDimension)
    }
}

private operator fun Point.times(zoom: Double): Point {
    return Point((x * zoom).toInt(), (y * zoom).toInt())
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