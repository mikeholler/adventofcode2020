import java.io.File

val lineRegex = Regex("^([0-9]+)-([0-9]+) (.): (.*)$")

data class PasswordToValidate(
    val char: Char,
    val range: IntRange,
    val password: String
)

fun PasswordToValidate.isValid1(): Boolean {
    return password.count { it == char } in range
}

fun PasswordToValidate.isValid2(): Boolean {
    return listOf(password[range.first - 1], password[range.last - 1])
        .count { it == char } == 1
}

fun String.toPasswordToValidateOrNull(): PasswordToValidate? {
    return lineRegex.matchEntire(this)?.let {m ->
        PasswordToValidate(
            char = m.groupValues[3].first(),
            range = m.groupValues[1].toInt()..m.groupValues[2].toInt(),
            password = m.groupValues[4]
        )
    }
}

val lines = File(args[0])
    .readLines()
    .mapNotNull { it.toPasswordToValidateOrNull() }

val result1 = lines.filter { it.isValid1() }.count()
println("Valid passwords (part 1): $result1")

val result2 = lines.filter { it.isValid2() }.count()
println("Valid passwords (part 2): $result2")
