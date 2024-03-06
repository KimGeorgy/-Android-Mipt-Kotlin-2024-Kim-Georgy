fun main() {
    val (ax, ay, bx, by) = readln().split(' ').map(String::toDouble)
    val a = Point(ax, ay)
    val b = Point(bx, by)
    val n = readln().toInt()
    val intersectionPoints: MutableSet<Point> = mutableSetOf()
    for (i in 1..n) {
        val (cx, cy, dx, dy) = readln().split(' ').map(String::toDouble)
        val c = Point(cx, cy)
        val d = Point(dx, dy)
        val intPoint = findIntersection(a, b, c, d)
        if (intPoint != null) {
            intersectionPoints.add(intPoint)
        }
    }

    println(intersectionPoints.size)
}

data class Point(var x: Double, var y: Double)

fun det (a: Double, b: Double, c: Double, d: Double): Double {
    return a * d - b * c
}

fun findIntersection(a: Point, b: Point, c: Point, d: Point): Point? {
    val a1 = a.y - b.y
    val b1 = b.x - a.x
    val c1 = -a1 * a.x - b1 * a.y
    val a2 = c.y - d.y
    val b2 = d.x - c.x
    val c2 = -a2 * c.x - b2 * c.y
    val zn = det(a1, b1, a2, b2)
    return if (zn != .0) {
        val x = -det(c1, b1, c2, b2) / zn
        val y = -det(a1, c1, a2, c2) / zn
        if (x <= maxOf(a.x, b.x) && x <= maxOf(c.x, d.x)
            && x >= minOf(a.x, b.x) && x >= minOf(c.x, d.x)
            && y <= maxOf(a.y, b.y) && y <= maxOf(c.y, d.y)
            && y >= minOf(a.y, b.y) && y >= minOf(c.y, d.y))
            Point(x, y)
        else null
    } else null
}