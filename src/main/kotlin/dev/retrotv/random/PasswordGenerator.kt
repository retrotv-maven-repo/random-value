package dev.retrotv.random

import dev.retrotv.random.enums.SecurityStrength
import dev.retrotv.random.enums.SecurityStrength.*
import dev.retrotv.random.value.getCapitalLetters
import dev.retrotv.random.value.getNumbers
import dev.retrotv.random.value.getSmallLetters
import dev.retrotv.random.value.getSpecialChars

/**
 * 무작위 패스워드 생성을 위한 클래스 입니다.
 * @see SecurityStrength enum을 통해 생성될 패스워드의 강도를 설정할 수 있습니다.
 *
 * @property securityStrength 생성할 패스워드 보안 강도
 * @constructor 빈 PasswordGenerator 클래스 생성
 */
class PasswordGenerator(private val securityStrength: SecurityStrength): Generator {
    private var generatedValue: String? = null

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

    private fun generateValue(len: Int): String? {
        val rv = when (securityStrength) {
            LOW -> {
                RandomValueGenerator(getSmallLetters(), getNumbers())
            }

            MIDDLE -> {
                RandomValueGenerator(getSmallLetters(), getNumbers(), getCapitalLetters())
            }

            HIGH -> {
                RandomValueGenerator(getSmallLetters(), getNumbers(), getCapitalLetters(), getSpecialChars())
            }
        }

        rv.generate(len)
        return rv.getValue()
    }
}