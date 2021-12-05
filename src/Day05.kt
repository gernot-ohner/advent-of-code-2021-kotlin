import kotlin.math.max
import kotlin.math.min

data class Line(val start: Point, val end: Point) {
    companion object {
        private val regex = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")
        fun fromString(s: String): Line {
            val match = regex.find(s)
            val (x1, y1, x2, y2) = match!!.destructured.toList().map { it.toInt() }
            return Line(Point(x1, y1), Point(x2, y2))
        }
    }

    fun points(): List<Point> =
        if (start.x == end.x) {
            (min(start.y, end.y)..max(start.y, end.y)).map { Point(start.x, it) }
        } else if (start.y == end.y) {
            (min(start.x, end.x)..max(start.x, end.x)).map { Point(it, start.y) }
        } else {
            emptyList()
        }
}

data class Point(val x: Int, val y: Int)

object Day05 {

    fun part1(input: List<String>): Int {
        val lines = input.map { Line.fromString(it) }
        val map = createMap(lines)


        lines.forEach { line ->
            line.points().forEach { point ->
                map[point.y][point.x] += 1
            }
        }

        return map
            .map { row -> row.filter { dangerLevel -> dangerLevel >= 2 } }
            .flatten()
            .count()
    }

    private fun createMap(lines: List<Line>): Array<IntArray> {
        val maxX = lines.maxOf { line -> max(line.start.x, line.end.x) } + 1
        val maxY = lines.maxOf { line -> max(line.start.y, line.end.y) } + 1
        return Array(maxY) { _ -> IntArray(maxX) { 0 } }
    }
}

fun main() {
    val testInput = readInput("5", "test")
    val testResult = Day05.part1(testInput)
    check(testResult == 5)
//    check(part2(testInput) == 250)

    val input = readInput("5", "prod")
    println(Day05.part1(input))
//    println(part2(input))
}
