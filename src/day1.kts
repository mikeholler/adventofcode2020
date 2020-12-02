/**
 * Usage: day1.kts <input-file>
 *
 * Given a list of numbers, find two numbers that sum to 2020 and multiply
 * them together. The input file must have lines containing numbers separated
 * by newlines.
 */
import java.io.File

val target = 2020

fun List<Int>.twoProduct(): Sequence<Pair<Int, Int>> = sequence {
    forEach { a ->
        forEach { b ->
            yield(a to b)
        }
    }
}

fun List<Int>.threeProduct(): Sequence<List<Int>> = sequence {
    forEach { c ->
        yieldAll(twoProduct().map { it.toList().plus(c) })
    }
}

val input = File(args[0])
    .readLines()
    .mapNotNull { it.toIntOrNull() }
    .distinct()

val firstAnswer = input
    .twoProduct()
    .find { it.toList().sum() == target}
    ?.let { "The answer is: ${it.first * it.second}" }
    ?: "Unable to find an answer that sums to $target"

println(firstAnswer)

val secondAnswer = input
    .threeProduct()
    .find { it.sum() == target}
    ?.let { "The answer is: ${it.reduce { acc, i -> acc * i }}" }
    ?: "Unable to find an answer that sums to $target"

println(secondAnswer)


