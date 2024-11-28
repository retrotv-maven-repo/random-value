package dev.retrotv.random

import dev.retrotv.data.utils.NumberUtils
import dev.retrotv.data.utils.StringUtils
import dev.retrotv.random.enums.SecurityStrength
import java.lang.IllegalArgumentException
import java.util.Random

// 모든 CharGroup에서 최소 한글자 이상 보장하는 옵션
private var isAllCharGroupLeastOne: Boolean = true

// 모든 CharGroup에서 동일한 확률로 값이 선택되도록 하는 옵션
private var isEqualDistribution: Boolean = true

// 영소문자 그룹
private val SMALL_LETTERS = charArrayOf(
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
)

// 영대문자 그룹
private val CAPITAL_LETTERS = charArrayOf(
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
)

// 숫자 그룹
private val NUMBERS = charArrayOf(
    '0', '1', '2', '3', '4',
    '5', '6', '7', '8', '9'
)

// 특수문자 그룹
private val SPECIAL_CHARS = charArrayOf(
    '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':',
    ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'
)

/**
 * 범용적으로 사용할 수 있는 무작위 값 생성 클래스 입니다.
 * 기본적으로 모든 문자 그룹에서 최소 한글자 이상 보장하는 isAllCharGroupLeastOne 옵션과,
 * 모든 문자 그룹에서 동일한 확률로 선택되도록 하는 isEqualDistribution 옵션이 활성화 됩니다.
 *
 * @author  yjj8353
 * @since   1.0.0
 * @property securityStrength 무작위 값의 보안 강도
 * @property random 무작위 값을 생성할 Random 객체
 * @constructor StringGenerator 클래스 생성
 */
abstract class StringGenerator(
    private val securityStrength: SecurityStrength,
    private val random: Random
): RandomGenerator<String> {
    private var allCharGroup: MutableList<CharArray> = mutableListOf()
    private var allCharGroupLeastCommonMultiple = 0

    // securityStrength에 따라 사용할 문자 그룹을 설정
    private val charGroups = getCharsGroup()
    init {
        val sizeArray: MutableList<Int> = mutableListOf()
        this.charGroups.forEach {
            allCharGroup.add(it)
            sizeArray.add(it.size)
        }

        allCharGroupLeastCommonMultiple = if (securityStrength != SecurityStrength.ONLY_NUMBER) {
            NumberUtils.leastCommonMultiple(*sizeArray.toIntArray())
        } else {
            sizeArray[0]
        }
    }

    /**
     * 지정한 길이만큼 무작위 문자열을 생성하고 반환합니다.
     *
     * @author yjj8353
     * @since 1.0.0
     * @param len 생성할 무작위 문자열의 길이
     * @return 생성된 무작위 문자열
     * @throws IllegalArgumentException len이 0보다 작으면 던져짐
     */
    override fun generate(len: Int): String {
        require(len > 0) { "생성할 무작위 값 길이 len은 0보다 작을 수 없습니다." }
        return generateValue(len)
    }

    /**
     * 모든 CharGroup에서 각각, 최소 하나의 값을 보장하도록 설정합니다.
     *
     * @author yjj8353
     * @since 1.0.0
     */
    fun enableAllCharGroupLeastOne() {
        isAllCharGroupLeastOne = true
    }

    /**
     * 모든 CharGroup에서 각각, 최소 하나의 값을 보장하지 않아도 상관 없도록 설정합니다.
     *
     * @author yjj8353
     * @since 1.0.0
     */
    fun disableAllCharGroupLeastOne() {
        isAllCharGroupLeastOne = false
    }

    /**
     * 모든 CharGroup에서 동일한 확률로 값이 선택되도록 설정합니다.
     *
     * @author yjj8353
     * @since 1.0.0
     */
    fun enableEqualDistribution() {
        isEqualDistribution = true
    }

    /**
     * 모든 CharGroup에서 동일하지 않은 확률로 값이 선택되지 않아도 상관 없도록 설정합니다.
     *
     * @author yjj8353
     * @since 1.0.0
     */
    fun disableEqualDistribution() {
        isEqualDistribution = false
    }

    // 지정된 길이(len)와 각종 옵션 값에 근거하여 무작위 문자열을 생성하고 반환합니다.
    private fun generateValue(len: Int): String {

        // 문자 그룹의 개수가 생성할 무작위 문자열의 길이보다 클 경우, 모든 문자 그룹에서 한 글자 이상 보장할 수 없으므로 예외 발생
        if (isAllCharGroupLeastOne && allCharGroup.size > len) {
            val message = """len 값이 allCharGroup.size 보다 작습니다.
            |생성할 문자열 길이의 값은 모든 문자 그룹의 개수보다 크거나 같아야 합니다.
            |이 오류를 무시하려면 generate 메소드 실행 전에 disableAllCharGroupLeastOne 메소드를 실행해
            |모든 문자 그룹에서 최소 한글자 이상 보장하는 옵션을 비활성화 하십시오.
            """.trimMargin()

            throw IllegalArgumentException(message)
        }

        val fullChars = getFullChars()
        val allLeastOneChars = if (isAllCharGroupLeastOne) { getAllCharGroupLeastOne() } else { null }

        var ca = CharArray(len)
        var i = allLeastOneChars?.size ?: 0
        while (i < len) {
            val random: Int = random.nextInt(fullChars.size)
            ca[i] = fullChars[random]
            i++
        }

        ca = if (isAllCharGroupLeastOne) {
            System.arraycopy(
                allLeastOneChars!!,
                0,
                ca,
                0,
                allLeastOneChars.size
            )

            return StringUtils.scrambleChars(String(ca))
        } else {
            ca
        }

        return String(ca)
    }

    // 모든 CharGroup을 묶어, 하나의 CharArray(char[])로 변환해 반환합니다.
    private fun getFullChars(): CharArray {
        val fullChars = CharArray(allCharGroupLeastCommonMultiple * allCharGroup.size)
        var destPos = 0

        allCharGroup.forEach{
            val len = allCharGroupLeastCommonMultiple/it.size
            for (i in 1..len) {
                System.arraycopy(
                    it,
                    0,
                    fullChars,
                    destPos,
                    it.size
                )
                destPos += it.size
            }
        }

        return fullChars
    }

    /*
     * 모든 CharsGroup에서, 각각 하나의 문자를 무작위로 뽑아 반환합니다.
     * 예를 들어 소문자/숫자 CharGroup이 존재하는 경우, 소문자 한 글자와 숫자 한 글자를 반환합니다.
     */
    private fun getAllCharGroupLeastOne(): CharArray {
        val acglo = CharArray(allCharGroup.size)
        allCharGroup.forEachIndexed { i, it ->
            val sr = this.random
            val random = sr.nextInt(it.size)
            acglo[i] = it[random]
        }

        return acglo
    }

    // 보안 강도에 따라 사용할 문자 그룹을 설정합니다.
    private fun getCharsGroup(): Array<CharArray> {
        return when (securityStrength) {
            SecurityStrength.ONLY_NUMBER -> {
                arrayOf(NUMBERS)
            }

            SecurityStrength.LOW -> {
                arrayOf(SMALL_LETTERS, NUMBERS)
            }

            SecurityStrength.MIDDLE -> {
                arrayOf(SMALL_LETTERS, CAPITAL_LETTERS, NUMBERS)
            }

            else -> {
                arrayOf(SMALL_LETTERS, CAPITAL_LETTERS, NUMBERS, SPECIAL_CHARS)
            }
        }
    }
}
