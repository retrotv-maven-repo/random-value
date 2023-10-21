package dev.retrotv.random

import dev.retrotv.data.utils.leastCommonMultiple
import dev.retrotv.random.enums.SecurityStrength
import dev.retrotv.random.enums.SecurityStrength.*
import dev.retrotv.random.value.getCapitalLetters
import dev.retrotv.random.value.getNumbers
import dev.retrotv.random.value.getSmallLetters
import dev.retrotv.random.value.getSpecialChars
import java.security.SecureRandom

val DEFAULT_SECURITY_STRENGTH = MIDDLE
const val DEFAULT_GENERATE_LENGTH = 16
const val DEFAULT_EQUAL_DISTRIBUTION = true

val CAPITAL_LETTERS = getCapitalLetters()
val SMALL_LETTERS = getSmallLetters()
val NUMBERS = getNumbers()
val SPECIAL_CHARS = getSpecialChars()

val CAPITAL_LETTERS_LENGTH = CAPITAL_LETTERS.size
val SMALL_LETTERS_LENGTH = SMALL_LETTERS.size
val NUMBERS_LENGTH = NUMBERS.size
val SPECIAL_CHARS_LENGTH = SPECIAL_CHARS.size

class RandomValue(
    private var len: Int = DEFAULT_GENERATE_LENGTH,
    private var securityStrength: SecurityStrength = DEFAULT_SECURITY_STRENGTH,
    private var equalDistribution: Boolean = DEFAULT_EQUAL_DISTRIBUTION
) {
    private var generatedValue: String? = null

    fun generate() {
        require(len > 0) { "생성할 무작위 값 길이 len은 0보다 작을 수 없습니다." }
        generatedValue = getValues(equalDistribution, securityStrength)
    }

    fun getValue(): String? {
        return generatedValue
    }

    private fun getValues(equalDistribution: Boolean, securityStrength: SecurityStrength): String {
        val range: Int
        val charSet: CharArray
        var i = 0

        val sb = StringBuilder()
        val sr = SecureRandom()

        return if (equalDistribution) {
            when (securityStrength) {
                LOW -> {
                    range = leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH) * 2
                    charSet = getLowStrengthChars()
                }

                MIDDLE -> {
                    range = leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH, CAPITAL_LETTERS_LENGTH) * 3
                    charSet = getMiddleStrengthChars()
                }

                HIGH -> {
                    range = leastCommonMultiple(
                        SMALL_LETTERS_LENGTH,
                        NUMBERS_LENGTH,
                        CAPITAL_LETTERS_LENGTH,
                        SPECIAL_CHARS_LENGTH
                    ) * 4
                    charSet = getHighStrengthChars()
                }
            }

            while (i < len) {
                val random: Int = sr.nextInt(range)
                sb.append(charSet[random])
                i++
            }

            sb.toString()
        } else {
            when (securityStrength) {
                LOW -> {
                    range = SMALL_LETTERS_LENGTH + NUMBERS_LENGTH
                    charSet = getLowStrengthChars()
                }

                MIDDLE -> {
                    range = CAPITAL_LETTERS_LENGTH + SMALL_LETTERS_LENGTH + NUMBERS_LENGTH
                    charSet = getMiddleStrengthChars()
                }

                HIGH -> {
                    range = CAPITAL_LETTERS_LENGTH + SMALL_LETTERS_LENGTH + NUMBERS_LENGTH + SPECIAL_CHARS_LENGTH
                    charSet = getHighStrengthChars()
                }
            }

            while (i < len) {
                val random: Int = sr.nextInt(range)
                sb.append(charSet[random])
                i++
            }

            return sb.toString()
        }
    }

    private fun getLowStrengthChars(): CharArray {
        val leastCommonMultiple = leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH)
        val arraySize = if (equalDistribution) {
            leastCommonMultiple * 2
        } else {
            SMALL_LETTERS_LENGTH + NUMBERS_LENGTH
        }
        val chars = CharArray(arraySize)

        var len = if (equalDistribution) leastCommonMultiple/SMALL_LETTERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                SMALL_LETTERS,
                0,
                chars,
                (i - 1) * SMALL_LETTERS_LENGTH,
                SMALL_LETTERS_LENGTH
            )
        }

        len = if (equalDistribution) leastCommonMultiple/NUMBERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                NUMBERS,
                0,
                chars,
                if (equalDistribution) leastCommonMultiple + (i - 1) * NUMBERS_LENGTH else SMALL_LETTERS_LENGTH,
                NUMBERS_LENGTH
            )
        }

        return chars
    }

    private fun getMiddleStrengthChars(): CharArray {
        val leastCommonMultiple = leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH, CAPITAL_LETTERS_LENGTH)
        val arraySize = if (equalDistribution) {
            leastCommonMultiple * 3
        } else {
            SMALL_LETTERS_LENGTH + NUMBERS_LENGTH + CAPITAL_LETTERS_LENGTH
        }
        val chars = CharArray(arraySize)

        var len = if (equalDistribution) leastCommonMultiple/SMALL_LETTERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                SMALL_LETTERS,
                0,
                chars,
                (i - 1) * SMALL_LETTERS_LENGTH,
                SMALL_LETTERS_LENGTH
            )
        }

        len = if (equalDistribution) leastCommonMultiple/NUMBERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                NUMBERS,
                0,
                chars,
                if (equalDistribution) leastCommonMultiple + (i - 1) * NUMBERS_LENGTH else SMALL_LETTERS_LENGTH,
                NUMBERS_LENGTH
            )
        }

        len = if (equalDistribution) leastCommonMultiple/CAPITAL_LETTERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                CAPITAL_LETTERS,
                0,
                chars,
                if (equalDistribution) (leastCommonMultiple * 2) + ((i - 1) * CAPITAL_LETTERS_LENGTH) else SMALL_LETTERS_LENGTH + NUMBERS_LENGTH,
                CAPITAL_LETTERS_LENGTH
            )
        }

        return chars
    }

    private fun getHighStrengthChars(): CharArray {
        val leastCommonMultiple = leastCommonMultiple(
            SMALL_LETTERS_LENGTH,
            NUMBERS_LENGTH,
            CAPITAL_LETTERS_LENGTH,
            SPECIAL_CHARS_LENGTH
        )
        val arraySize = if (equalDistribution) {
            leastCommonMultiple * 4
        } else {
            SMALL_LETTERS_LENGTH + NUMBERS_LENGTH + CAPITAL_LETTERS_LENGTH + SPECIAL_CHARS_LENGTH
        }
        val chars = CharArray(arraySize)

        var len = if (equalDistribution) leastCommonMultiple/SMALL_LETTERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                SMALL_LETTERS,
                0,
                chars,
                (i - 1) * SMALL_LETTERS_LENGTH,
                SMALL_LETTERS_LENGTH
            )
        }

        len = if (equalDistribution) leastCommonMultiple/NUMBERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                NUMBERS,
                0,
                chars,
                if (equalDistribution) leastCommonMultiple + (i - 1) * NUMBERS_LENGTH else SMALL_LETTERS_LENGTH,
                NUMBERS_LENGTH
            )
        }

        len = if (equalDistribution) leastCommonMultiple/CAPITAL_LETTERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                CAPITAL_LETTERS,
                0,
                chars,
                if (equalDistribution) (leastCommonMultiple * 2) + ((i - 1) * CAPITAL_LETTERS_LENGTH) else SMALL_LETTERS_LENGTH + NUMBERS_LENGTH,
                CAPITAL_LETTERS_LENGTH
            )
        }

        len = if (equalDistribution) leastCommonMultiple/SPECIAL_CHARS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                SPECIAL_CHARS,
                0,
                chars,
                if (equalDistribution) (leastCommonMultiple * 3) + ((i - 1) * SPECIAL_CHARS_LENGTH) else SMALL_LETTERS_LENGTH + NUMBERS_LENGTH + CAPITAL_LETTERS_LENGTH,
                SPECIAL_CHARS_LENGTH
            )
        }

        return chars
    }
}
