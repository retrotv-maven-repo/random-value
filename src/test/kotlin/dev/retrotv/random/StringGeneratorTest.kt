package dev.retrotv.random

import dev.retrotv.random.enums.SecurityStrength
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.*


class StringGeneratorTest {

    @Nested
    @DisplayName("SecurityStrength 분기 테스트")
    inner class TestSecurityStrength {

        @DisplayName("SecurityStrength.LOW 테스트")
        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        fun test_low() {
            val passwordGenerator = PasswordGenerator(SecurityStrength.LOW, SecureRandom())
            passwordGenerator.generate(8)
            passwordGenerator.enableAllCharGroupLeastOne()
            val generatedValue = passwordGenerator.getValue()

            assertFalse(generatedValue.contains(Regex(".*[A-Z]+")))
            assertTrue(generatedValue.contains(Regex(".*[a-z]+")))
            assertTrue(generatedValue.contains(Regex(".*[0-9]+")))
            assertFalse(generatedValue.contains(Regex(".*[^A-Za-z0-9]+")))
        }
        @DisplayName("SecurityStrength.MEDIUM 테스트")
        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        fun test_medium() {
            val passwordGenerator = PasswordGenerator(SecurityStrength.MIDDLE, SecureRandom())
            passwordGenerator.generate(8)
            passwordGenerator.enableAllCharGroupLeastOne()
            val generatedValue = passwordGenerator.getValue()

            assertTrue(generatedValue.contains(Regex(".*[A-Z]+")))
            assertTrue(generatedValue.contains(Regex(".*[a-z]+")))
            assertTrue(generatedValue.contains(Regex(".*[0-9]+")))
            assertFalse(generatedValue.contains(Regex(".*[^A-Za-z0-9]+")))
        }

        @DisplayName("SecurityStrength.HIGH 테스트")
        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        fun test_high() {
            val passwordGenerator = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
            passwordGenerator.generate(8)
            passwordGenerator.enableAllCharGroupLeastOne()
            val generatedValue = passwordGenerator.getValue()

            assertTrue(generatedValue.contains(Regex(".*[A-Z]+")))
            assertTrue(generatedValue.contains(Regex(".*[a-z]+")))
            assertTrue(generatedValue.contains(Regex(".*[0-9]+")))
            assertTrue(generatedValue.contains(Regex(".*[^A-Za-z0-9]+")))
        }
    }

    @Test
    @DisplayName("IllegalArgumentException 발생 테스트")
    fun test_illegalArgumentException() {
        val passwordGenerator = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
        assertThrows(IllegalArgumentException::class.java) {
            passwordGenerator.enableAllCharGroupLeastOne()
            passwordGenerator.generate(1)
        }
    }

    @Nested
    @DisplayName("isAllCharGroupLeastOne 분기 테스트")
    inner class TestIsAllCharGroupLeastOne {

        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        @DisplayName("모든 CharGroup에서 각각, 최소 하나의 값을 보장하도록 설정")
        fun test_enableAllCharGroupLeastOne() {
            val passwordGenerator = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
            passwordGenerator.enableAllCharGroupLeastOne()
            passwordGenerator.generate(8)
            val generatedValue = passwordGenerator.getValue()

            assertTrue(generatedValue.contains(Regex(".*[A-Z]+")))
            assertTrue(generatedValue.contains(Regex(".*[a-z]+")))
            assertTrue(generatedValue.contains(Regex(".*[0-9]+")))
            assertTrue(generatedValue.contains(Regex(".*[^A-Za-z0-9]+")))
        }

        @Test
        @DisplayName("모든 CharGroup에서 각각, 최소 하나의 값을 보장하지 않도록 설정")
        fun test_disableAllCharGroupLeastOne() {
            val passwordGenerator = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
            passwordGenerator.disableAllCharGroupLeastOne()
            passwordGenerator.generate(8)
            val generatedValue = passwordGenerator.getValue()

            assertNotNull(generatedValue)
            assertEquals(8, generatedValue.length)
        }
    }

    @Nested
    @DisplayName("isEqualDistribution 분기 테스트")
    inner class TestIsEqualDistribution {

        @Test
        @DisplayName("모든 CharGroup에서 동일한 확률로 값이 선택되도록 설정")
        fun test_enableEqualDistribution() {
            val passwordGenerator = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
            passwordGenerator.enableEqualDistribution()
            passwordGenerator.generate(8)
            val generatedValue = passwordGenerator.getValue()

            assertNotNull(generatedValue)
            assertEquals(8, generatedValue.length)
        }

        @Test
        @DisplayName("모든 CharGroup에서 동일하지 않은 확률로 값이 선택되지 않도록 설정")
        fun test_disableEqualDistribution() {
            val passwordGenerator = PasswordGenerator(SecurityStrength.HIGH, SecureRandom())
            passwordGenerator.disableEqualDistribution()
            passwordGenerator.generate(8)
            val generatedValue = passwordGenerator.getValue()

            assertNotNull(generatedValue)
            assertEquals(8, generatedValue.length)
        }
    }
}