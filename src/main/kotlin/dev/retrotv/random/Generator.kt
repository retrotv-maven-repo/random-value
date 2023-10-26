package dev.retrotv.random

interface Generator {
    fun generate(len: Int)
    fun getValue(): String?
    fun getBytes(): ByteArray?
}