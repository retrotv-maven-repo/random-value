package dev.retrotv.random

import dev.retrotv.random.value.getCapitalLetters
import dev.retrotv.random.value.getNumbers
import dev.retrotv.random.value.getSmallLetters
import dev.retrotv.random.value.getSpecialChars
import java.security.SecureRandom
import kotlin.test.Test

class RandomStringRandomGeneratorTest {

    @Test
    fun test() {
        val rp = UniversalRandomStringGenerator(getSmallLetters(), getNumbers(), getCapitalLetters(), getSpecialChars())
        rp.disableAllCharGroupLeastOne()
        rp.generate(20, SecureRandom())
        println(rp.getValue())
    }
}