import kotlin.math.max

fun main() {
    val testInput = readInput("5", "test")
    check(Day05.part1(testInput) == 5)
    check(Day05.part2(testInput) == 12)

    val input = readInput("5", "prod")
    println(Day05.part1(input))
    println(Day05.part2(input))
}

object Day05 {
    fun part1(input: List<String>): Int = compute(input, false)
    fun part2(input: List<String>): Int = compute(input, true)

    private fun compute(input: List<String>, diagonalsAllowed: Boolean): Int {
        val lines = input.map { Line.fromString(it) }
        val map = createMap(lines)
        lines.forEach { line ->
            line.points(diagonalsAllowed).forEach { point ->
                map[point.y][point.x] += 1
            }
        }
        return countDangerousLocations(map)
    }

    private fun createMap(lines: List<Line>): Array<IntArray> {
        val maxX = lines.maxOf { line -> max(line.start.x, line.end.x) } + 1
        val maxY = lines.maxOf { line -> max(line.start.y, line.end.y) } + 1
        return Array(maxY) { _ -> IntArray(maxX) { 0 } }
    }

    private fun countDangerousLocations(map: Array<IntArray>) = map
        .map { row -> row.filter { dangerLevel -> dangerLevel >= 2 } }
        .flatten()
        .count()
}

// ===================
// Data Classes
// ===================

data class Line(val start: Point, val end: Point) {
    companion object {
        private val regex = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")
        fun fromString(s: String): Line {
            val match = regex.find(s)
            val (x1, y1, x2, y2) = match!!.destructured.toList().map { it.toInt() }
            return Line(Point(x1, y1), Point(x2, y2))
        }
    }

    fun points(diagonalsAllowed: Boolean): List<Point> {
        val pairs = if (xCoords().size == yCoords().size) {
            if (diagonalsAllowed) xCoords().zip(yCoords())
            else emptyList()
        } else xCoords().product(yCoords())
        return pairs.map { Point(it.first, it.second) }
    }

    private fun xCoords(): List<Int> =
        Pair(start.x, end.x).range()

    private fun yCoords(): List<Int> =
        Pair(start.y, end.y).range()
}

data class Point(val x: Int, val y: Int)

// ===================
// Extension Functions
// ===================

fun <T, U> List<T>.product(other: List<U>): List<Pair<T, U>> =
    this.flatMap { t -> other.map { u -> Pair(t, u) } }

fun Pair<Int, Int>.range(): List<Int> {
    val step = this.second.compareTo(first)
    return if (step == 0) listOf(this.first)
    else IntProgression.fromClosedRange(this.first, this.second, step).toList()
}
