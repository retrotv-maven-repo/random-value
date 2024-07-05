package dev.retrotv.random

import java.security.SecureRandom
import kotlin.test.Test

class DoubleGeneratorTest {

    @Test
    fun test_DeciamlGenerator() {
        val doubleGenerator = DoubleGenerator(SecureRandom())
        doubleGenerator.generate(16)
        val value = doubleGenerator.getValue()
        println(value)
    }
}