fun main() {
    val testInput = readInput("2", "test")
    check(Day02.part1(testInput) == 150)
    check(Day02.part2(testInput) == 900)

    val input = readInput("2", "prod")
    println(Day02.part1(input))
    println(Day02.part2(input))
}

typealias InstructionApplication = (Coordinates, Instruction) -> Coordinates

object Day02 {
    fun part1(input: List<String>) = compute(input, instructionSet1)
    fun part2(input: List<String>) = compute(input, instructionSet2)

    private fun compute(input: List<String>, instructionSet: Map<String, InstructionApplication>): Int =
        input
            .mapNotNull { Instruction.fromString(it) }
            .filter { instructionSet.containsKey(it.direction) }
            .fold(Coordinates(0, 0, 0)) { coords, instruction ->
                instructionSet[instruction.direction]!!.invoke(coords, instruction)
            }
            .score()

    private val instructionSet1 = mapOf<String, InstructionApplication>(
        "forward" to { coords, instruction -> coords.copy(horizontal = coords.horizontal + instruction.distance) },
        "down" to { coords, instruction -> coords.copy(vertical = coords.vertical + instruction.distance) },
        "up" to { coords, instruction -> coords.copy(vertical = coords.vertical - instruction.distance) }
    )

    private val instructionSet2 = mapOf<String, InstructionApplication>(
        "forward" to { coords, instruction ->
            coords.copy(
                horizontal = coords.horizontal + instruction.distance,
                vertical = coords.vertical + coords.aim * instruction.distance
            )
        },
        "down" to { coords, instruction -> coords.copy(aim = coords.aim + instruction.distance) },
        "up" to { coords, instruction -> coords.copy(aim = coords.aim - instruction.distance) }
    )
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
