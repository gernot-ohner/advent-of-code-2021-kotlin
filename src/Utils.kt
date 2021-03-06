import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: String, testOrProd: String) =
    File("inputs/day$day", "$testOrProd.txt").readLines()

fun readInputNumbers(day: String, testOrProd: String) =
    readInput(day, testOrProd).first()
        .split(",")
        .map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
