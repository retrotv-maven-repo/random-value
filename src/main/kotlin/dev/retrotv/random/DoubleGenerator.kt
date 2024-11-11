package dev.retrotv.random

import java.util.Random
import kotlin.math.pow
import kotlin.math.round
import kotlin.properties.Delegates

/**
 * 무작위 실수 데이터 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 1.0.0
 * @property random 무작위 데이터 생성을 위한 Random 객체
 * @constructor 빈 DoubleGenerator 클래스 생성
 */
class DoubleGenerator(private val random: Random): RandomGenerator<Double> {
    private var randomData by Delegates.notNull<Double>()

    override fun generate(len: Int): Double {
        require(len in 1..16) { "len은 0보다 크고 16보다 작거나 같아야 합니다." }
        randomData = round(random.nextDouble() * 10.0.pow(len)) / 10.0.pow(len)

        return randomData
    }
}