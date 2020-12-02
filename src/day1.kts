/**
 * Usage: day1.kts <input-file>
 *
 * Given a list of numbers, find two numbers that sum to 2020 and multiply
 * them together. The input file must have lines containing numbers separated
 * by newlines.
 */
import java.io.File

val target = 2020

fun List<Int>.product(): Sequence<Pair<Int, Int>> {
    return sequence {
        forEach { a ->
            forEach { b ->
                yield(a to b)
            }
        }
    }
}


val output = File(args[0])
    .readLines()
    .mapNotNull { it.toIntOrNull() }
    .distinct()
    .product()
    .find { it.first + it.second == target}
    ?.let { "The answer is: ${it.first * it.second}" }
    ?: "Unable to find an answer that sums to $target"

println(output)


