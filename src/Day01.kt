fun main() {
    val testInput = readInput("1", "test")
    check(Day01.part1(testInput) == 7)
    check(Day01.part2(testInput) == 5)

    val input = readInput("1", "prod")
    println(Day01.part1(input))
    println(Day01.part2(input))
}

object Day01 {
    fun part1(input: List<String>): Int = input
        .map { it.toInt() }
        .windowed(2)
        .count { (i1, i2) -> i2 > i1 }

    fun part2(input: List<String>): Int = input
        .map { it.toInt() }
        .windowed(3, transform = { it.sum() })
        .windowed(2)
        .count { (i1, i2) -> i2 > i1 }
}
