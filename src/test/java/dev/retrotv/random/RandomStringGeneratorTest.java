package dev.retrotv.random;

import dev.retrotv.random.value.CapitalLetters;
import dev.retrotv.random.value.Numbers;
import dev.retrotv.random.value.SmallLetters;
import dev.retrotv.random.value.SpecialChars;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

class RandomStringGeneratorTest {

    @Test
    void test_randomStringGenerator() {
        UniversalRandomStringGenerator ursg = new UniversalRandomStringGenerator(
                SmallLetters.getSmallLetters(),
                CapitalLetters.getCapitalLetters(),
                Numbers.getNumbers(),
                SpecialChars.getSpecialChars()
        );

        ursg.disableAllCharGroupLeastOne();
        ursg.generate(20, new SecureRandom());
        System.out.println(ursg.getValue());
    }
}
