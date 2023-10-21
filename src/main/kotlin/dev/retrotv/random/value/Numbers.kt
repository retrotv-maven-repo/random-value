@file:JvmName("Numbers")
package dev.retrotv.random.value

private val NUMBERS = charArrayOf(
    '0', '1', '2', '3', '4',
    '5', '6', '7', '8', '9'
)

fun getNumbers(): CharArray {
    return NUMBERS
}