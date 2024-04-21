package dev.retrotv.random

import java.util.*

/**
 * 랜덤한 값 생성을 위한 클래스를 구현하기 위해 상속 받아야하는 인터페이스 입니다.
 */
interface RandomGenerator<T> {

    /**
     * 지정한 길이만큼 무작위 데이터를 생성합니다.
     *
     * @param len 생성할 문자열의 길이
     * @param random
     */
    fun generate(len: Int, random: Random)

    /**
     * generate 메소드를 통해 생성된 데이터를 ByteArray(byte[]) 형식으로 반환합니다.
     *
     * @return generate 메소드를 통해 생성된 데이터
     */
    fun getValue(): T
}