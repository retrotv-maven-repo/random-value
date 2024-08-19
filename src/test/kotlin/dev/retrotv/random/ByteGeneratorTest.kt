package dev.retrotv.random

import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.assertNotNull

class ByteGeneratorTest {

    @Test
    fun test_ByteGenerator() {
        val byteGenerator = ByteGenerator(SecureRandom())
        assertNotNull(byteGenerator.generate(24))
    }
}