package dev.retrotv.random

import dev.retrotv.random.enums.SecurityStrength
import dev.retrotv.random.value.getCapitalLetters
import dev.retrotv.random.value.getNumbers
import dev.retrotv.random.value.getSmallLetters
import kotlin.test.Test

class RandomValueGeneratorTest {

    @Test
    fun test() {
        val rp = RandomValueGenerator(getSmallLetters(), getNumbers(), getCapitalLetters())
        rp.generate(16)
        println(rp.getValue())
    }
}