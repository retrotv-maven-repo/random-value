package dev.retrotv.random

import dev.retrotv.random.value.getCapitalLetters
import dev.retrotv.random.value.getNumbers
import dev.retrotv.random.value.getSmallLetters
import kotlin.test.Test

class RandomPasswordTest {

    @Test
    fun test() {
        val rp = RandomPassword(charGroup = arrayOf(getCapitalLetters(), getSmallLetters(), getNumbers()))
        rp.generate(16)
    }
}