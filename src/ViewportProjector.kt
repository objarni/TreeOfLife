package TreeOfLife

import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle

class ViewportProjector(var centerEyeWorld: Point, var viewportSize: Dimension, var zoom: Double = 1.0) {
    fun projectRectangle(bottomLeftW: Point, sizeW: Dimension): Rectangle {
        // W = world coordinates, V = viewport coordinates
        val topLeftW = bottomLeftW + Point(0, sizeW.height)
        val topLeftV = projectPoint(topLeftW)
        var dimV = sizeW * zoom
        return Rectangle(topLeftV, dimV)
    }

    fun projectPoint(pointW: Point): Point {
        val eyeToPointTopLeftW = pointW - centerEyeWorld
        val eyeToPointTopLeftScaledToV = eyeToPointTopLeftW * zoom
        val eyeToPointTopLeftV =  Point(eyeToPointTopLeftScaledToV.x, -eyeToPointTopLeftScaledToV.y)
        val centerV = viewportSize / 2
        val topLeftV = centerV + eyeToPointTopLeftV
        return topLeftV
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

private operator fun Point.minus(pt: Point): Point {
    return Point(x - pt.x, y - pt.y)
}