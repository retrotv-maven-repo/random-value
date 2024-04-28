package dev.retrotv.random

import dev.retrotv.random.enums.SecurityStrength
import java.security.SecureRandom
import kotlin.test.Test

class RandomStringRandomGeneratorTest {

    @Test
    fun test() {
        val passwordGenerator = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
        passwordGenerator.generate(8)
        println(passwordGenerator.getValue())

        val otpGenerator = OTPGenerator(SecureRandom())
        otpGenerator.generate(6)
        println(otpGenerator.getValue())
    }
}