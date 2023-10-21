@file:JvmName("SpecialChars")
package dev.retrotv.random.value

private val SPECIAL_CHARS = charArrayOf(
    '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':',
    ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'
)

fun getSpecialChars(): CharArray {
    return SPECIAL_CHARS
}