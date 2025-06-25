package dev.retrotv.random.generator;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static dev.retrotv.random.enums.SecurityStrength.*;

class PasswordGeneratorTest {

    @Test
    void test() {
        PasswordGenerator generator = new PasswordGenerator(new SecureRandom(), HIGH);
        generator.disableEqualDistribution();
        String password = generator.generate();
        System.out.println(password);
    }
}
