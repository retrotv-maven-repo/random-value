package dev.retrotv.random

import dev.retrotv.data.utils.ByteUtils.toHexString
import dev.retrotv.random.enums.SecurityStrength
import java.security.SecureRandom
import kotlin.test.Test

class StringGeneratorTest {

    @Test
    fun test() {
        val passwordGenerator = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
        passwordGenerator.generate(8)
        println(passwordGenerator.getValue())

        val otpGenerator = PINGenerator(SecureRandom())
        otpGenerator.generate(6)
        println(otpGenerator.getValue())
    }

    @Test
    fun test_secureRandom() {
        val sr1 = SecureRandom()
        val sr2 = SecureRandom()
        val randomValue1 = ByteArray(128)
        val randomValue2 = ByteArray(128)
        sr1.setSeed(1234567890L)
        sr2.setSeed(1234567890L)
        sr1.nextBytes(randomValue1)
        sr2.nextBytes(randomValue2)

        println(toHexString(randomValue1))
        println(toHexString(randomValue2))
    }
}