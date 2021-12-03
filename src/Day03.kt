// there's probably an O(1) space solution
// but for now I'm going to go with the O(n)
fun part1(input: List<String>): Int {
    val lineLength = input.first().length
    val rowNum = input.size
    val initialCount = List(lineLength) { 0 }
    val oneCounts = input.fold(initialCount) { l, s ->
        s.toCharArray()
            .map { toInt(it) }
            .zip(l)
            .map { (i1, i2) -> i1 + i2 }
    }
    val majorityElements = oneCounts
        .map { it > rowNum / 2 }
    val gamma = majorityElements
        .map { it.toChar() }
        .joinToString("")
        .toInt(2)
    val epsilon = majorityElements
        .map { (!it).toChar() }
        .joinToString("", "", "")
        .toInt(2)
    println(gamma)
    println(epsilon)
    return gamma * epsilon
}


fun Boolean.toChar(): Char = ((if (this) 1 else 0) + '0'.code).toChar()
fun toInt(c: Char): Int = c.code - '0'.code


fun main() {
    val testInput = readInput("3", "test")
    check(part1(testInput) == 198)
//    check(Day2Part2.compute(testInput) == 900)

    val input = readInput("3", "prod")
    println(part1(input))
//    println(Day2Part2.compute(input))
}
