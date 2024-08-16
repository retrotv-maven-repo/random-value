package dev.retrotv.random

import java.security.SecureRandom
import org.junit.jupiter.api.*
import kotlin.test.*

class PINGeneratorTest {

    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    @DisplayName("PIN 생성 테스트")
    fun test_generate() {
        val pinGenerator = PINGenerator(SecureRandom())
        pinGenerator.generate(6)
        val generatedValue = pinGenerator.getValue()

        assertTrue(generatedValue.length == 6)
        assertFalse(generatedValue.contains(Regex(".*[A-Z]+")))
        assertFalse(generatedValue.contains(Regex(".*[a-z]+")))
        assertTrue(generatedValue.contains(Regex(".*[0-9]+")))
        assertFalse(generatedValue.contains(Regex(".*[^A-Za-z0-9]+")))
    }
}