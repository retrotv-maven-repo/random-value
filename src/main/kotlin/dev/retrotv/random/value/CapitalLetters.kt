@file:JvmName("CapitalLetters")
package dev.retrotv.random.value

private val CAPITAL_LETTERS = charArrayOf(
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
)

fun getCapitalLetters(): CharArray {
    return CAPITAL_LETTERS
}