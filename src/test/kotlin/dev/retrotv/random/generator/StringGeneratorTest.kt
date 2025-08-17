package dev.retrotv.random.generator

import dev.retrotv.random.enums.SecurityStrength.*
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
            val passwordGenerator = PasswordGenerator(SecureRandom(), LOW)
            passwordGenerator.enableAllCharGroupLeastOne()
            passwordGenerator.setLength(8)
            val password = passwordGenerator.generate()

            assertFalse(password.contains(Regex(".*[A-Z]+")))
            assertTrue(password.contains(Regex(".*[a-z]+")))
            assertTrue(password.contains(Regex(".*[0-9]+")))
            assertFalse(password.contains(Regex(".*[^A-Za-z0-9]+")))
        }

        @DisplayName("SecurityStrength.MIDDLE 테스트")
        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        fun test_medium() {
            val passwordGenerator = PasswordGenerator(SecureRandom(), MIDDLE)
            passwordGenerator.enableAllCharGroupLeastOne()
            passwordGenerator.setLength(8)
            val password = passwordGenerator.generate()

            assertTrue(password.contains(Regex(".*[A-Z]+")))
            assertTrue(password.contains(Regex(".*[a-z]+")))
            assertTrue(password.contains(Regex(".*[0-9]+")))
            assertFalse(password.contains(Regex(".*[^A-Za-z0-9]+")))
        }

        @DisplayName("SecurityStrength.HIGH 테스트")
        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        fun test_high() {
            val passwordGenerator = PasswordGenerator(SecureRandom(), HIGH)
            passwordGenerator.enableAllCharGroupLeastOne()
            passwordGenerator.setLength(8)
            val password = passwordGenerator.generate()

            println(password)

            assertTrue(password.contains(Regex(".*[A-Z]+")))
            assertTrue(password.contains(Regex(".*[a-z]+")))
            assertTrue(password.contains(Regex(".*[0-9]+")))
            assertTrue(password.contains(Regex(".*[^A-Za-z0-9]+")))
        }
    }

    @Test
    @DisplayName("IllegalArgumentException 발생 테스트")
    fun test_illegalArgumentException() {
        val passwordGenerator = PasswordGenerator(SecureRandom(), HIGH)
        assertThrows(IllegalArgumentException::class.java) {
            passwordGenerator.enableAllCharGroupLeastOne()
            passwordGenerator.setLength(1)
            passwordGenerator.generate()
        }
    }

    @Nested
    @DisplayName("isAllCharGroupLeastOne 분기 테스트")
    inner class TestIsAllCharGroupLeastOne {

        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        @DisplayName("모든 CharGroup에서 각각, 최소 하나의 값을 보장하도록 설정")
        fun test_enableAllCharGroupLeastOne() {
            val passwordGenerator = PasswordGenerator(SecureRandom(), HIGH)
            passwordGenerator.enableAllCharGroupLeastOne()
            passwordGenerator.setLength(8)
            val password = passwordGenerator.generate()

            assertTrue(password.contains(Regex(".*[A-Z]+")))
            assertTrue(password.contains(Regex(".*[a-z]+")))
            assertTrue(password.contains(Regex(".*[0-9]+")))
            assertTrue(password.contains(Regex(".*[^A-Za-z0-9]+")))
        }

        @Test
        @DisplayName("모든 CharGroup에서 각각, 최소 하나의 값을 보장하지 않도록 설정")
        fun test_disableAllCharGroupLeastOne() {
            val passwordGenerator = PasswordGenerator(SecureRandom(), HIGH)
            passwordGenerator.disableAllCharGroupLeastOne()
            passwordGenerator.setLength(8)
            val password = passwordGenerator.generate()

            assertNotNull(password)
            assertEquals(8, password.length)
        }
    }

    @Nested
    @DisplayName("isEqualDistribution 분기 테스트")
    inner class TestIsEqualDistribution {

        @Test
        @DisplayName("모든 CharGroup에서 동일한 확률로 값이 선택되도록 설정")
        fun test_enableEqualDistribution() {
            val passwordGenerator = PasswordGenerator(SecureRandom(), HIGH)
            passwordGenerator.enableEqualDistribution()
            passwordGenerator.setLength(8)
            val password = passwordGenerator.generate()

            assertNotNull(password)
            assertEquals(8, password.length)
        }

        @Test
        @DisplayName("모든 CharGroup에서 동일하지 않은 확률로 값이 선택되지 않도록 설정")
        fun test_disableEqualDistribution() {
            val passwordGenerator = PasswordGenerator(SecureRandom(), HIGH)
            passwordGenerator.disableEqualDistribution()
            passwordGenerator.setLength(8)
            val password = passwordGenerator.generate()

            assertNotNull(password)
            assertEquals(8, password.length)
        }
    }
}