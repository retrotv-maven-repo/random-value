package dev.retrotv.random;

import dev.retrotv.random.enums.SecurityStrength;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

class RandomStringGeneratorTest {

    @Test
    void test_randomStringGenerator() {
        PasswordGenerator pg = new PasswordGenerator(SecurityStrength.HIGH, new SecureRandom());
        pg.generate(8);
        System.out.println(pg.getValue());
    }
}
