package dev.retrotv.random.generator

import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.assertNotNull

class ByteGeneratorTest {

    @Test
    fun test_ByteGenerator() {
        val byteGenerator = ByteGenerator(SecureRandom())
        assertNotNull(byteGenerator.generate())
    }
}