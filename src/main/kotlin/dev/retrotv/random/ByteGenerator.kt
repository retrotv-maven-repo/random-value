package dev.retrotv.random

import java.util.Random

/**
 * 무작위 바이트 데이터 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 1.0.0
 * @property random 무작위 데이터 생성을 위한 Random 객체
 * @constructor 빈 ByteGenerator 클래스 생성
 */
class ByteGenerator(private val random: Random): RandomGenerator<ByteArray> {
    private lateinit var randomData: ByteArray

    /**
     * 지정한 길이만큼 무작위 바이트 데이터를 생성하고 반환합니다.
     *
     * @param len 생성할 바이트 데이터의 길이
     * @return 생성된 바이트 데이터
     */
    override fun generate(len: Int): ByteArray {
        randomData = ByteArray(len)
        random.nextBytes(randomData)

        return randomData
    }
}