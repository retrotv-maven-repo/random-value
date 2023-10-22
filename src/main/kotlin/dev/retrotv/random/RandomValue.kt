package dev.retrotv.random

import dev.retrotv.data.utils.leastCommonMultiple
import dev.retrotv.random.enums.SecurityStrength
import dev.retrotv.random.enums.SecurityStrength.*
import dev.retrotv.random.value.getCapitalLetters
import dev.retrotv.random.value.getNumbers
import dev.retrotv.random.value.getSmallLetters
import dev.retrotv.random.value.getSpecialChars
import java.security.SecureRandom
import java.util.*

val DEFAULT_SECURITY_STRENGTH = MIDDLE
const val DEFAULT_GENERATE_LENGTH = 16
const val DEFAULT_EQUAL_DISTRIBUTION = true
const val DEFAULT_ALL_CHAR_GROUP_LEAST_ONE = false

val CAPITAL_LETTERS = getCapitalLetters()
val SMALL_LETTERS = getSmallLetters()
val NUMBERS = getNumbers()
val SPECIAL_CHARS = getSpecialChars()

val CAPITAL_LETTERS_LENGTH = CAPITAL_LETTERS.size
val SMALL_LETTERS_LENGTH = SMALL_LETTERS.size
val NUMBERS_LENGTH = NUMBERS.size
val SPECIAL_CHARS_LENGTH = SPECIAL_CHARS.size

const val LENGTH_MUST_BIGGER_THEN_ZERO = "생성할 무작위 값 길이 len은 0보다 작을 수 없습니다."

/**
 * 무작위 값을 생성하기 위한 클래스 입니다.
 *
 * @property len 생성할 무작위 값의 길이
 * @property securityStrength 생성할 무작위 값의 강도
 * @property equalDistribution 균등 확률 설정 여부
 * @property allCharGroupLeastOne 모든 문자열 그룹에서 최소 한 글자 이상 보장할지 여부
 * @constructor RandomValue 객체를 생성합니다. 모든 property 값은 지정되지 않으면 기본 값으로 설정됩니다.
 */
class RandomValue @JvmOverloads constructor(
    private var len: Int = DEFAULT_GENERATE_LENGTH,
    private var securityStrength: SecurityStrength = DEFAULT_SECURITY_STRENGTH,
    private var equalDistribution: Boolean = DEFAULT_EQUAL_DISTRIBUTION,
    private var allCharGroupLeastOne: Boolean = DEFAULT_ALL_CHAR_GROUP_LEAST_ONE
) {
    private var generatedValue: String? = null

    /**
     * 무작위 값을 생성합니다.
     */
    fun generate() {
        require(len > 0) { LENGTH_MUST_BIGGER_THEN_ZERO }
        generatedValue = generateValue(equalDistribution, securityStrength)
    }

    /**
     * 생성한 무작위 값을 문자열로 반환합니다.
     *
     * @return String 형태의 무작위 값
     */
    fun getValue(): String? {
        return generatedValue
    }

    /**
     * 생성한 무작위 값을 ByteArray(byte[])로 반환합니다.
     *
     * @return ByteArray(byte[]) 형태의 무작위 값
     */
    fun getBytes(): ByteArray? {
        return generatedValue?.toByteArray()
    }

    private fun generateValue(equalDistribution: Boolean, securityStrength: SecurityStrength): String {
        val range: Int
        val charSet: CharArray

        val sb = StringBuilder()
        val sr = SecureRandom()

        when (securityStrength) {
            LOW -> {
                range = if (equalDistribution) {
                    leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH) * 2
                } else {
                    SMALL_LETTERS_LENGTH + NUMBERS_LENGTH
                }
                charSet = getLowStrengthChars()
            }

            MIDDLE -> {
                range = if (equalDistribution) {
                    leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH, CAPITAL_LETTERS_LENGTH) * 3
                } else {
                    CAPITAL_LETTERS_LENGTH + SMALL_LETTERS_LENGTH + NUMBERS_LENGTH
                }
                charSet = getMiddleStrengthChars()
            }

            HIGH -> {
                range = if (equalDistribution) {
                    leastCommonMultiple(
                        SMALL_LETTERS_LENGTH,
                        NUMBERS_LENGTH,
                        CAPITAL_LETTERS_LENGTH,
                        SPECIAL_CHARS_LENGTH
                    ) * 4
                } else {
                    CAPITAL_LETTERS_LENGTH + SMALL_LETTERS_LENGTH + NUMBERS_LENGTH + SPECIAL_CHARS_LENGTH
                }
                charSet = getHighStrengthChars()
            }
        }

        var i = 0
        while (i < len) {
            val random: Int = sr.nextInt(range)
            sb.append(charSet[random])
            i++
        }

        return sb.toString()
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

    fun arraycopy(source: Arrays, target: Arrays, targetPos: Int, len: Int) {
        System.arraycopy(
            source,
            0,
            target,
            targetPos,
            len
        )
    }
}
