package dev.retrotv.random

import java.security.SecureRandom
import kotlin.test.Test

class ByteGeneratorTest {

    @Test
    fun test_ByteGenerator() {
        val byteGenerator = ByteGenerator(SecureRandom())
        byteGenerator.generate(24)
        val randomData = byteGenerator.getValue()
        println(randomData.size)
    }
}