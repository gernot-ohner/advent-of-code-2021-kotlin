fun main() {
    val testInput = readInput("6", "test")
    check(Day06.part1(testInput) == 5934L)

    val input = readInput("6", "prod")
    println(Day06.part1(input))
    println(Day06.part2(input))
}

object Day06 {
    fun part1(input: List<String>): Long = compute(input, 80)
    fun part2(input: List<String>): Long = compute(input, 256)

    private fun compute(input: List<String>, numberOfDays: Int): Long =
        rec(0, numberOfDays, countPopulation(input.first())).sum()

    private fun rec(generation: Int, limit: Int, population: Array<Long>): Array<Long> =
        if (generation >= limit) population
        else rec(generation + 1, limit, updatePopulation(population))

    private fun countPopulation(s: String): Array<Long> {
        val population = Array(9) { 0L }
        s.split(",")
            .map { it.toInt() }
            .forEach { population[it] += 1L }
        return population
    }

    private fun updatePopulation(currentPopulation: Array<Long>): Array<Long> {
        val zero = currentPopulation[0]
        return currentPopulation
            .drop(1)
            .plus(zero)
            .mapIndexed { index, l -> l + if (index == 6) zero else 0 }
            .toTypedArray()
    }
}
