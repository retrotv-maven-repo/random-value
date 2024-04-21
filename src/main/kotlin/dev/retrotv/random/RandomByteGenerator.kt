package dev.retrotv.random

import java.util.Random

class RandomByteGenerator: RandomGenerator<ByteArray> {
    private lateinit var randomData: ByteArray

    override fun generate(len: Int, random: Random) {
        randomData = ByteArray(len)
        random.nextBytes(randomData)
    }

    override fun getValue(): ByteArray {
        return randomData
    }
}