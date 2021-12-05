fun main() {
    val testInput = readInput("2", "test")
    check(Day02.part1(testInput) == 150)
    check(Day02.part2(testInput) == 900)

    val input = readInput("2", "prod")
    println(Day02.part1(input))
    println(Day02.part2(input))
}

object Day02 {
    fun part1(input: List<String>) = compute(input, "part1")
    fun part2(input: List<String>) = compute(input, "part2")

    private fun compute(input: List<String>, instructionSet: String): Int {
        val initialCoordinates = Coordinates(0, 0, 0)
        val updateInstructions =
            if (instructionSet == "part1")
                this::updateLocationPart1
            else
                this::updateLocationPart2

        return input
            .mapNotNull { Instruction.fromString(it) }
            .fold(initialCoordinates, updateInstructions)
            .score()
    }

    private fun updateLocationPart1(acc: Coordinates, instruction: Instruction) =
        when (instruction.direction) {
            "forward" -> acc.copy(horizontal = acc.horizontal + instruction.distance)
            "down" -> acc.copy(vertical = acc.vertical + instruction.distance)
            "up" -> acc.copy(vertical = acc.vertical - instruction.distance)
            else -> throw IllegalArgumentException("unexpected instruction direction")
        }

    private fun updateLocationPart2(acc: Coordinates, instruction: Instruction) =
        when (instruction.direction) {
            "forward" -> acc.copy(
                horizontal = acc.horizontal + instruction.distance,
                vertical = acc.vertical + acc.aim * instruction.distance
            )
            "down" -> acc.copy(aim = acc.aim + instruction.distance)
            "up" -> acc.copy(aim = acc.aim - instruction.distance)
            else -> throw IllegalArgumentException("unexpected instruction direction")
        }
}

// ===================
// Data Classes
// ===================

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
