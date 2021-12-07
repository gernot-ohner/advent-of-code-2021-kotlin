fun main() {
    val testInput = readInput("3", "test")
    check(Day03.part1(testInput) == 198)
    check(Day03.part2(testInput) == 230)

    val input = readInput("3", "prod")
    println(Day03.part1(input))
    println(Day03.part2(input))
}

object Day03 {

    fun part1(input: List<String>): Int {
        val elementLength = input.first().length
        val chars = (0 until elementLength)
            .map { i -> findMajority(input.map { it[i] }) }
        val gamma = chars
            .toBinaryInt()
        val epsilon = chars
            .map { it.reverseZeroAndOne() }
            .toBinaryInt()
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val oxygen = rec(0, input, false)
        val co2 = rec(0, input, true)
        return oxygen * co2
    }

    private fun rec(i: Int, input: List<String>, carbon: Boolean): Int =
        if (i >= input.first().length || input.size == 1)
            input.first().toInt(2)
        else {
            val majority = findMajority(input.map { it[i] })
            rec(i + 1, input.filter { (it[i] == majority) xor carbon }, carbon)
        }

    private fun findMajority(chars: List<Char>): Char =
        if (chars.count { it == '1' } >= chars.size / 2.0) '1' else '0'
}
