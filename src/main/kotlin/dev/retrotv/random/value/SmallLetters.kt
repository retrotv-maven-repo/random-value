@file:JvmName("SmallLetters")
package dev.retrotv.random.value

private val SMALL_LETTERS = charArrayOf(
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
)

fun getSmallLetters(): CharArray {
    return SMALL_LETTERS
}