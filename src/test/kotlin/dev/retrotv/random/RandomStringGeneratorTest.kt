package dev.retrotv.random

import dev.retrotv.random.value.getCapitalLetters
import dev.retrotv.random.value.getNumbers
import dev.retrotv.random.value.getSmallLetters
import dev.retrotv.random.value.getSpecialChars
import kotlin.test.Test

class RandomStringGeneratorTest {

    @Test
    fun test() {
        val rp = UniversalRandomStringGenerator(getSmallLetters(), getNumbers(), getCapitalLetters(), getSpecialChars())
        rp.disableAllCharGroupLeastOne()
        rp.generate(20)
        println(rp.getString())
    }
}