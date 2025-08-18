package dev.retrotv.random.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class PINGeneratorTest {

    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    @DisplayName("PIN 생성 테스트")
    void test_pinGenerator() {
        PINGenerator pinGenerator = new PINGenerator(new SecureRandom());
        String pin = pinGenerator.generate();

        assertEquals(4, pin.length());
        assertFalse(pin.matches(".*[A-Z]+"));
        assertFalse(pin.matches(".*[a-z]+"));
        assertTrue(pin.matches(".*[0-9]+"));
        assertFalse(pin.matches(".*[^A-Za-z0-9]+"));
    }
}
