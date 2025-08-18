package dev.retrotv.random.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static dev.retrotv.random.enums.SecurityStrength.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PasswordGeneratorTest {

    @Test
    @DisplayName("PasswordGenerator 테스트")
    void test_passwordGenerator() {
        PasswordGenerator generator = new PasswordGenerator(new SecureRandom(), HIGH);
        generator.disableEqualDistribution();
        String password = generator.generate();
        assertNotNull(password);
    }
}
