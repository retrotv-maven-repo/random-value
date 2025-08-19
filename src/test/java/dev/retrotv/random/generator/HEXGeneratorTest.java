package dev.retrotv.random.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HEXGeneratorTest {

    @Test
    @DisplayName("HEXGenerator 테스트")
    void test_hexGenerator() {
        HEXGenerator hexGenerator = new HEXGenerator(new SecureRandom());
        String generatedValue = hexGenerator.generate();

        assertTrue(generatedValue.matches("^[0-9A-Fa-f]+$"));
    }
}
