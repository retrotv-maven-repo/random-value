package dev.retrotv.random

import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.assertNotNull

class DoubleGeneratorTest {

    @Test
    fun test_DeciamlGenerator() {
        val doubleGenerator = DoubleGenerator(SecureRandom())
        assertNotNull(doubleGenerator.generate(16))
    }
}