package dev.retrotv.random

import dev.retrotv.data.utils.leastCommonMultiple
import dev.retrotv.data.utils.scrambleChars
import java.security.SecureRandom

const val DEFAULT_ALL_CHAR_GROUP_LEAST_ONE = true
const val DEFAULT_EQUAL_DISTRIBUTION = true

var isAllCharGroupLeastOne: Boolean = DEFAULT_ALL_CHAR_GROUP_LEAST_ONE
var isEqualDistribution: Boolean = DEFAULT_EQUAL_DISTRIBUTION
var allCharGroup: MutableList<CharArray> = mutableListOf()
var allCharGroupLeastCommonMultiple: Int = 0

class RandomValueGenerator(vararg charGroup: CharArray) : Generator {
    private var generatedValue: String? = null

    init {
        val sizeArray: MutableList<Int> = mutableListOf()
        charGroup.forEach {
            allCharGroup.add(it)
            sizeArray.add(it.size)
        }
        allCharGroupLeastCommonMultiple = leastCommonMultiple(*sizeArray.toIntArray())
    }

    override fun generate(len: Int) {
        require(len > 0) { "생성할 무작위 값 길이 len은 0보다 작을 수 없습니다." }
        generatedValue = generateValue(len)
    }

    override fun getValue(): String? {
        return generatedValue
    }

    override fun getBytes(): ByteArray? {
        return generatedValue?.toByteArray()
    }

    /**
     * 모든 CharGroup에서 각각, 최소 하나의 값을 보장하도록 설정합니다.
     */
    fun enableAllCharGroupLeastOne() {
        isAllCharGroupLeastOne = true
    }

    /**
     * 모든 CharGroup에서 각각, 최소 하나의 값을 보장하지 않도록 설정합니다.
     */
    fun disableAllCharGroupLeastOne() {
        isAllCharGroupLeastOne = false
    }

    /**
     * 모든 CharGroup에서 동일한 확률로 값이 선택되도록 설정합니다.
     */
    fun enableEqualDistribution() {
        isEqualDistribution = true
    }

    /**
     * 모든 CharGroup에서 동일하지 않은 확률로 값이 선택되도록 설정합니다.
     */
    fun disableEqualDistribution() {
        isEqualDistribution = false
    }

    /*
     * 지정된 길이(len)과 각종 옵션 값에 근거하여 무작위 문자열을 생성하고 반환합니다.
     */
    private fun generateValue(len: Int): String {
        val fullChars = getFullChars()
        val allLeastOneChars = if (isAllCharGroupLeastOne) { getAllCharGroupLeastOne() } else { null }

        val sr = SecureRandom()
        var ca = CharArray(len)
        var i = allLeastOneChars?.size ?: 0
        while (i < len) {
            val random: Int = sr.nextInt(fullChars.size)
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

            return scrambleChars(String(ca))
        } else {
            ca
        }

        return String(ca)
    }

    /*
     * 모든 CharGroup을 묶어, 하나의 CharArray(char[])로 변환해 반환합니다.
     */
    private fun getFullChars(): CharArray {
        val fullChars = CharArray(allCharGroupLeastCommonMultiple * allCharGroup.size)
        var destPos = 0
        allCharGroup.forEach{
            val len = allCharGroupLeastCommonMultiple/it.size
            for (i: Int in 1..len) {
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
            val sr = SecureRandom()
            val random = sr.nextInt(it.size)
            acglo[i] = it[random]
        }

        return acglo
    }
}
