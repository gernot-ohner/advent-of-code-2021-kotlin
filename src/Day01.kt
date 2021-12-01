fun main() {
    fun part1(input: List<String>): Int = input
        .map { it.toInt() }
        .windowed(2)
        .count { (i1, i2) -> i2 > i1 }

    fun part2(input: List<String>): Int = input
        .map { it.toInt() }
        .windowed(3, transform = { it.sum() })
        .windowed(2)
        .count { (i1, i2) -> i2 > i1 }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("1", "test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("1", "prod")
    println(part1(input))
    println(part2(input))
}
