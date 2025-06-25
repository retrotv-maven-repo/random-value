package dev.retrotv.random;

import dev.retrotv.random.enums.SecurityStrength;
import dev.retrotv.random.generator.PasswordGenerator;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static dev.retrotv.random.enums.SecurityStrength.HIGH;
import static org.junit.jupiter.api.Assertions.*;

class StringGeneratorJavaTest {

    @Test
    void test_randomStringGenerator() {
        PasswordGenerator pg = new PasswordGenerator(new SecureRandom(), HIGH);
        assertNotNull(pg.generate());
    }
}
