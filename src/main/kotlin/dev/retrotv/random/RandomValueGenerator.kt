package dev.retrotv.random

import dev.retrotv.data.utils.leastCommonMultiple
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

    fun enableAllCharGroupLeastOne() {
        isAllCharGroupLeastOne = true
    }

    fun disableAllCharGroupLeastOne() {
        isAllCharGroupLeastOne = false
    }

    fun enableEqualDistribution() {
        isEqualDistribution = true
    }

    fun disableEqualDistribution() {
        isEqualDistribution = false
    }

    private fun generateValue(len: Int): String {
        val fullChars = getFullChars()
        val allLeastOneChars = if (isAllCharGroupLeastOne) { getAllCharsGroupLeastOne() } else { null }

        val sr = SecureRandom()
        var ca = CharArray(len)
        var i = allLeastOneChars?.size ?: 0
        while (i < len) {
            val random: Int = sr.nextInt(fullChars.size)
            ca[i] = fullChars[random]
            i++
        }

        ca = if (isAllCharGroupLeastOne) { scrambleChars(ca, allLeastOneChars!!) } else { ca }
        return ca.contentToString()
    }

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

    private fun getAllCharsGroupLeastOne(): CharArray {
        val acglo = CharArray(allCharGroup.size)
        allCharGroup.forEachIndexed { i, it ->
            val sr = SecureRandom()
            val random = sr.nextInt(it.size)
            acglo[i] = it[random]
        }

        return acglo
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