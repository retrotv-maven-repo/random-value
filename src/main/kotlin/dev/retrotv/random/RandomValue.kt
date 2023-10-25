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
const val DEFAULT_IS_ALL_CHARS_GROUP_LEAST_ONE = false
const val DEFAULT_IS_EQUAL_DISTRIBUTION = true

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
 * @property isAllCharGroupLeastOne 모든 문자열 그룹에서 최소 한 글자 이상 보장 여부
 * @property isEqualDistribution 균등 확률 설정 여부
 * @constructor RandomValue 객체를 생성합니다. 모든 property 값은 지정되지 않으면 기본 값으로 설정됩니다.
 */
class RandomValue @JvmOverloads constructor(
    private var len: Int = DEFAULT_GENERATE_LENGTH,
    private var securityStrength: SecurityStrength = DEFAULT_SECURITY_STRENGTH,
    private var isAllCharGroupLeastOne: Boolean = DEFAULT_IS_ALL_CHARS_GROUP_LEAST_ONE,
    private var isEqualDistribution: Boolean = DEFAULT_IS_EQUAL_DISTRIBUTION
) {
    private var generatedValue: String? = null

    /**
     * 무작위 값을 생성합니다.
     */
    fun generate() {
        require(len > 0) { LENGTH_MUST_BIGGER_THEN_ZERO }
        generatedValue = generateValue()
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

    private fun generateValue(): String {
        val range: Int
        val charSet: CharArray

        val allLeastOneChars = getAllCharsGroupLeastOne()
        val sr = SecureRandom()

        when (securityStrength) {
            LOW -> {

                /*
                 * equalDistribution이 true일 경우, 모든 문자열 그룹(대소문자, 숫자, 특수문자)에서 동일한 확률로 선택될 수 있도록
                 * 각 문자열 그룹 크기의 최소공배수를 구해 charSet 크기를 지정합니다.
                 */
                range = if (isEqualDistribution) {
                    leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH) * 2
                } else {
                    SMALL_LETTERS_LENGTH + NUMBERS_LENGTH
                }
                charSet = getLowStrengthChars()
            }

            MIDDLE -> {
                range = if (isEqualDistribution) {
                    leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH, CAPITAL_LETTERS_LENGTH) * 3
                } else {
                    CAPITAL_LETTERS_LENGTH + SMALL_LETTERS_LENGTH + NUMBERS_LENGTH
                }
                charSet = getMiddleStrengthChars()
            }

            HIGH -> {
                range = if (isEqualDistribution) {
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

        val ca = CharArray(len)
        var i = allLeastOneChars?.size ?: 0
        while (i < len) {
            val random: Int = sr.nextInt(range)
            ca[i] = charSet[random]
            i++
        }

        println(allLeastOneChars)

        return if (isAllCharGroupLeastOne) scrambleChars(ca, allLeastOneChars!!).contentToString() else ca.contentToString()
    }

    private fun getLowStrengthChars(): CharArray {
        val leastCommonMultiple = leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH)
        val arraySize = if (isEqualDistribution) {
            leastCommonMultiple * 2
        } else {
            SMALL_LETTERS_LENGTH + NUMBERS_LENGTH
        }
        val chars = CharArray(arraySize)

        var len = if (isEqualDistribution) leastCommonMultiple/SMALL_LETTERS_LENGTH else 1
        var destPos = 0
        for (i: Int in 1..len) {
            System.arraycopy(
                SMALL_LETTERS,
                0,
                chars,
                destPos,
                SMALL_LETTERS_LENGTH
            )
            destPos += SMALL_LETTERS_LENGTH
        }

        len = if (isEqualDistribution) leastCommonMultiple/NUMBERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                NUMBERS,
                0,
                chars,
                destPos,
                NUMBERS_LENGTH
            )
            destPos += NUMBERS_LENGTH
        }

        return chars
    }

    private fun getMiddleStrengthChars(): CharArray {
        val leastCommonMultiple = leastCommonMultiple(SMALL_LETTERS_LENGTH, NUMBERS_LENGTH, CAPITAL_LETTERS_LENGTH)
        val arraySize = if (isEqualDistribution) {
            leastCommonMultiple * 3
        } else {
            SMALL_LETTERS_LENGTH + NUMBERS_LENGTH + CAPITAL_LETTERS_LENGTH
        }
        val chars = CharArray(arraySize)

        var len = if (isEqualDistribution) leastCommonMultiple/SMALL_LETTERS_LENGTH else 1
        var destPos = 0
        for (i: Int in 1..len) {
            System.arraycopy(
                SMALL_LETTERS,
                0,
                chars,
                destPos,
                SMALL_LETTERS_LENGTH
            )
            destPos += SMALL_LETTERS_LENGTH
        }

        len = if (isEqualDistribution) leastCommonMultiple/NUMBERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                NUMBERS,
                0,
                chars,
                destPos,
                NUMBERS_LENGTH
            )
            destPos += NUMBERS_LENGTH
        }

        len = if (isEqualDistribution) leastCommonMultiple/CAPITAL_LETTERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                CAPITAL_LETTERS,
                0,
                chars,
                destPos,
                CAPITAL_LETTERS_LENGTH
            )
            destPos += CAPITAL_LETTERS_LENGTH
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
        val arraySize = if (isEqualDistribution) {
            leastCommonMultiple * 4
        } else {
            SMALL_LETTERS_LENGTH + NUMBERS_LENGTH + CAPITAL_LETTERS_LENGTH + SPECIAL_CHARS_LENGTH
        }
        val chars = CharArray(arraySize)

        var len = if (isEqualDistribution) leastCommonMultiple/SMALL_LETTERS_LENGTH else 1
        var destPos = 0
        for (i: Int in 1..len) {
            System.arraycopy(
                SMALL_LETTERS,
                0,
                chars,
                destPos,
                SMALL_LETTERS_LENGTH
            )
            destPos += SMALL_LETTERS_LENGTH
        }

        len = if (isEqualDistribution) leastCommonMultiple/NUMBERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                NUMBERS,
                0,
                chars,
                destPos,
                NUMBERS_LENGTH
            )
            destPos += NUMBERS_LENGTH
        }

        len = if (isEqualDistribution) leastCommonMultiple/CAPITAL_LETTERS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                CAPITAL_LETTERS,
                0,
                chars,
                destPos,
                CAPITAL_LETTERS_LENGTH
            )
            destPos += CAPITAL_LETTERS_LENGTH
        }

        len = if (isEqualDistribution) leastCommonMultiple/SPECIAL_CHARS_LENGTH else 1
        for (i: Int in 1..len) {
            System.arraycopy(
                SPECIAL_CHARS,
                0,
                chars,
                destPos,
                SPECIAL_CHARS_LENGTH
            )
            destPos += SPECIAL_CHARS_LENGTH
        }

        return chars
    }

    private fun getAllCharsGroupLeastOne(): CharArray? {
        if (!isAllCharGroupLeastOne) {
            return null
        }

        when (securityStrength) {
            LOW -> {
                val chars = CharArray(2)
                val sr = SecureRandom()
                var random = sr.nextInt(SMALL_LETTERS_LENGTH)
                chars[0] = SMALL_LETTERS[random]

                random = sr.nextInt(NUMBERS_LENGTH)
                chars[1] = NUMBERS[random]

                return chars
            }

            MIDDLE -> {
                val chars = CharArray(3)
                val sr = SecureRandom()
                var random = sr.nextInt(SMALL_LETTERS_LENGTH)
                chars[0] = SMALL_LETTERS[random]

                random = sr.nextInt(NUMBERS_LENGTH)
                chars[1] = NUMBERS[random]

                random = sr.nextInt(CAPITAL_LETTERS_LENGTH)
                chars[2] = CAPITAL_LETTERS[random]

                return chars
            }

            HIGH -> {
                val chars = CharArray(4)
                val sr = SecureRandom()
                var random = sr.nextInt(SMALL_LETTERS_LENGTH)
                chars[0] = SMALL_LETTERS[random]

                random = sr.nextInt(NUMBERS_LENGTH)
                chars[1] = NUMBERS[random]

                random = sr.nextInt(CAPITAL_LETTERS_LENGTH)
                chars[2] = CAPITAL_LETTERS[random]

                random = sr.nextInt(SPECIAL_CHARS_LENGTH)
                chars[3] = SPECIAL_CHARS[random]

                return chars
            }
        }
    }

    private fun scrambleChars(chars: CharArray, allLeastOneChars: CharArray): CharArray {
        System.arraycopy(
            allLeastOneChars,
            0,
            chars,
            0,
            allLeastOneChars.size
        )

        val orgCharMutableList = chars.toMutableList()
        val newCharArray = CharArray(chars.size)
        val sr = SecureRandom()

        var i = 0
        while (i < newCharArray.size) {
            val random: Int = sr.nextInt(orgCharMutableList.size)
            newCharArray[i] = orgCharMutableList[random]
            orgCharMutableList.removeAt(random)
            i++
        }

        return newCharArray
    }
}
