package dev.retrotv.random.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ByteGeneratorTest {

    @Test
    @DisplayName("ByteGenerator 테스트")
    void test_byteGenerator() {
        ByteGenerator byteGenerator = new ByteGenerator(new SecureRandom());
        assertNotNull(byteGenerator.generate());
    }
}
