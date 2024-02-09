package dev.retrotv.random

interface Generator {

    /**
     * 지정한 길이만큼 무작위 데이터를 생성합니다.
     *
     * @param len 생성할 문자열의 길이
     */
    fun generate(len: Int)

    /**
     * generate 메소드를 통해 생성된 데이터를 ByteArray(byte[]) 형식으로 반환합니다.
     *
     * @return generate 메소드를 통해 생성된 데이터
     */
    fun getBytes(): ByteArray
}