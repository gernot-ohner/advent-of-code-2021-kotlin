fun <T, U> List<T>.product(other: List<U>): List<Pair<T, U>> =
    this.flatMap { t -> other.map { u -> Pair(t, u) } }

fun Pair<Int, Int>.range(): List<Int> {
    val step = this.second.compareTo(first)
    return if (step == 0) listOf(this.first)
    else IntProgression.fromClosedRange(this.first, this.second, step).toList()
}

fun List<Char>.toBinaryInt(): Int =
    this.joinToString("").toInt(2)

fun Char.reverseZeroAndOne(): Char =
    if (this == '0') '1'
    else if (this == '1') '0'
    else this
