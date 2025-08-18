package dev.retrotv.random.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DoubleGeneratorTest {

    @Test
    @DisplayName("DoubleGenerator 테스트")
    void test_doubleGenerator() {
        DoubleGenerator doubleGenerator = new DoubleGenerator(new SecureRandom());
        Double generatedValue = doubleGenerator.generate();
        assertNotNull(generatedValue);
    }
}
