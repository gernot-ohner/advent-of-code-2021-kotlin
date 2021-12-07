import kotlin.math.abs

fun main() {
    val testInput = readInputNumbers("7", "test")
    check(Day07.part1(testInput) == 37)
    check(Day07.part2(testInput) == 168)

    val input = readInputNumbers("7", "prod")
    println(Day07.part1(input))
    println(Day07.part2(input))
}

object Day07 {
    fun part1(input: List<Int>): Int = compute(input) { it }
    fun part2(input: List<Int>): Int = compute(input) { it * (it + 1) / 2 }

    private fun compute(input: List<Int>, costFunction: (Int) -> Int): Int =
        (1..input.maxOrNull()!!)
            .map { i -> input.map { costFunction(abs(it - i)) } }
            .minOf { it.sum() }
}
