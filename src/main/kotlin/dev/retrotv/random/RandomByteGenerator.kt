package dev.retrotv.random

import java.security.SecureRandom

class RandomByteGenerator: Generator {
    private val sr = SecureRandom()
    private var randomData: ByteArray? = null

    override fun generate(len: Int) {
        randomData = ByteArray(len)
        sr.nextBytes(randomData)
    }

    override fun getBytes(): ByteArray? {
        return randomData
    }
}