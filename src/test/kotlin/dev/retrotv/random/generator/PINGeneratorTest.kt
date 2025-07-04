package dev.retrotv.random.generator

import java.security.SecureRandom
import org.junit.jupiter.api.*
import kotlin.test.*

class PINGeneratorTest {

    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    @DisplayName("PIN 생성 테스트")
    fun test_generate() {
        val pinGenerator = PINGenerator(SecureRandom())
        pinGenerator.setLength(6)
        val pin = pinGenerator.generate()

        assertTrue(pin.length == 6)
        assertFalse(pin.contains(Regex(".*[A-Z]+")))
        assertFalse(pin.contains(Regex(".*[a-z]+")))
        assertTrue(pin.contains(Regex(".*[0-9]+")))
        assertFalse(pin.contains(Regex(".*[^A-Za-z0-9]+")))
    }
}