package dev.retrotv.random

import dev.retrotv.data.utils.ByteUtils.toHexString
import dev.retrotv.random.enums.SecurityStrength
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.*


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
            assertTrue(generatedValue.contains(Regex(".*[\\\\!@#%^&*()_+-=\\[\\]{}|;':\",./<>?~`]+")))
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