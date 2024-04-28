package dev.retrotv.random

import java.util.Random

class RandomByteGenerator(private val random: Random): RandomGenerator<ByteArray> {
    private lateinit var randomData: ByteArray

    override fun generate(len: Int) {
        randomData = ByteArray(len)
        random.nextBytes(randomData)
    }

    override fun getValue(): ByteArray {
        return randomData
    }
}