data class Instruction(val direction: String, val distance: Int) {
    companion object {
        private val regex = Regex("(forward|up|down) (\\d+)")

        fun fromString(input: String): Instruction? {
            val match = regex.find(input) ?: return null
            val (direction, distance) = match.destructured
            return Instruction(direction, distance.toInt())
        }
    }
}

data class Coordinates(val horizontal: Int, val vertical: Int, val aim: Int) {
    fun score(): Int = horizontal * vertical
}

object Day2Part1 {
    fun compute(input: List<String>): Int = input
        .mapNotNull { Instruction.fromString(it) }
        .fold(Coordinates(0, 0, 0), this::updateLocation)
        .score()

    private fun updateLocation(acc: Coordinates, instruction: Instruction) =
        when (instruction.direction) {
            "forward" -> acc.copy(horizontal = acc.horizontal + instruction.distance)
            "down" -> acc.copy(vertical = acc.vertical + instruction.distance)
            "up" -> acc.copy(vertical = acc.vertical - instruction.distance)
            else -> throw IllegalArgumentException("unexpected instruction direction")
        }
}

object Day2Part2 {
    fun compute(input: List<String>): Int = input
        .mapNotNull { Instruction.fromString(it) }
        .fold(Coordinates(0, 0, 0), this::updateLocation)
        .score()

    private fun updateLocation(acc: Coordinates, instruction: Instruction) =
        when (instruction.direction) {
            "forward" -> acc.copy(
                horizontal = acc.horizontal + instruction.distance,
                vertical = acc.vertical + acc.aim * instruction.distance)
            "down" -> acc.copy(aim = acc.aim + instruction.distance)
            "up" -> acc.copy(aim = acc.aim - instruction.distance)
            else -> throw IllegalArgumentException("unexpected instruction direction")
        }
}

fun main() {
    val testInput = readInput("2", "test")
    check(Day2Part1.compute(testInput) == 150)
    check(Day2Part2.compute(testInput) == 900)

    val input = readInput("2", "prod")
    println(Day2Part1.compute(input))
    println(Day2Part2.compute(input))
}
