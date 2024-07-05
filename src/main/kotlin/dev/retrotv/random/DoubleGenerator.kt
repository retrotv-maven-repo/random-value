package dev.retrotv.random

import java.util.Random
import kotlin.math.pow
import kotlin.math.round
import kotlin.properties.Delegates

class DoubleGenerator(private val random: Random): RandomGenerator<Double> {
    private var randomData by Delegates.notNull<Double>()

    override fun generate(len: Int) {
        require(len in 1..16) { "len은 0보다 크고 16보다 작거나 같아야 합니다." }
        randomData = round(random.nextDouble() * 10.0.pow(len)) / 10.0.pow(len)
    }

    override fun getValue(): Double {
        return randomData
    }
}