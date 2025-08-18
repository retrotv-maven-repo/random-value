package dev.retrotv.random.generator;

import dev.retrotv.random.enums.SecurityStrength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class StringGeneratorTest {

    @Nested
    @DisplayName("SecurityStrength 분기 테스트")
    class TestSecurityStrength {

        @DisplayName("SecurityStrength.LOW 테스트")
        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        void test_low() {
            PasswordGenerator passwordGenerator = new PasswordGenerator(new SecureRandom(), SecurityStrength.LOW);
            passwordGenerator.enableAllCharGroupLeastOne();
            passwordGenerator.setLength(8);
            String password = passwordGenerator.generate();

            assertFalse(Pattern.compile(".*[A-Z]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[a-z]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[0-9]+").matcher(password).find());
            assertFalse(Pattern.compile(".*[^A-Za-z0-9]+").matcher(password).find());
        }

        @DisplayName("SecurityStrength.MIDDLE 테스트")
        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        void test_medium() {
            PasswordGenerator passwordGenerator = new PasswordGenerator(new SecureRandom(), SecurityStrength.MIDDLE);
            passwordGenerator.enableAllCharGroupLeastOne();
            passwordGenerator.setLength(8);
            String password = passwordGenerator.generate();

            assertTrue(Pattern.compile(".*[A-Z]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[a-z]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[0-9]+").matcher(password).find());
            assertFalse(Pattern.compile(".*[^A-Za-z0-9]+").matcher(password).find());
        }

        @DisplayName("SecurityStrength.HIGH 테스트")
        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        void test_high() {
            PasswordGenerator passwordGenerator = new PasswordGenerator(new SecureRandom(), SecurityStrength.HIGH);
            passwordGenerator.enableAllCharGroupLeastOne();
            passwordGenerator.setLength(8);
            String password = passwordGenerator.generate();

            assertTrue(Pattern.compile(".*[A-Z]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[a-z]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[0-9]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[^A-Za-z0-9]+").matcher(password).find());
        }
    }

    @Test
    @DisplayName("IllegalArgumentException 발생 테스트")
    void test_illegalArgumentException() {
        PasswordGenerator passwordGenerator = new PasswordGenerator(new SecureRandom(), SecurityStrength.HIGH);
        passwordGenerator.enableAllCharGroupLeastOne();
        passwordGenerator.setLength(1);
        assertThrows(IllegalArgumentException.class, passwordGenerator::generate);
    }

    @Nested
    @DisplayName("isAllCharGroupLeastOne 분기 테스트")
    class TestIsAllCharGroupLeastOne {

        @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
        @DisplayName("모든 CharGroup에서 각각, 최소 하나의 값을 보장하도록 설정")
        void test_enableAllCharGroupLeastOne() {
            PasswordGenerator passwordGenerator = new PasswordGenerator(new SecureRandom(), SecurityStrength.HIGH);
            passwordGenerator.enableAllCharGroupLeastOne();
            passwordGenerator.setLength(8);
            String password = passwordGenerator.generate();

            assertTrue(Pattern.compile(".*[A-Z]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[a-z]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[0-9]+").matcher(password).find());
            assertTrue(Pattern.compile(".*[^A-Za-z0-9]+").matcher(password).find());
        }

        @Test
        @DisplayName("모든 CharGroup에서 각각, 최소 하나의 값을 보장하지 않도록 설정")
        void test_disableAllCharGroupLeastOne() {
            PasswordGenerator passwordGenerator = new PasswordGenerator(new SecureRandom(), SecurityStrength.HIGH);
            passwordGenerator.disableAllCharGroupLeastOne();
            passwordGenerator.setLength(8);
            String password = passwordGenerator.generate();

            assertNotNull(password);
            assertEquals(8, password.length());
        }
    }

    @Nested
    @DisplayName("isEqualDistribution 분기 테스트")
    class TestIsEqualDistribution {

        @Test
        @DisplayName("모든 CharGroup에서 동일한 확률로 값이 선택되도록 설정")
        void test_enableEqualDistribution() {
            PasswordGenerator passwordGenerator = new PasswordGenerator(new SecureRandom(), SecurityStrength.HIGH);
            passwordGenerator.enableEqualDistribution();
            passwordGenerator.setLength(8);
            String password = passwordGenerator.generate();

            assertNotNull(password);
            assertEquals(8, password.length());
        }

        @Test
        @DisplayName("모든 CharGroup에서 동일하지 않은 확률로 값이 선택되지 않도록 설정")
        void test_disableEqualDistribution() {
            PasswordGenerator passwordGenerator = new PasswordGenerator(new SecureRandom(), SecurityStrength.HIGH);
            passwordGenerator.disableEqualDistribution();
            passwordGenerator.setLength(8);
            String password = passwordGenerator.generate();

            assertNotNull(password);
            assertEquals(8, password.length());
        }
    }
}
