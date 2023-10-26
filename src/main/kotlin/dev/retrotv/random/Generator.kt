package dev.retrotv.random

interface Generator {

    /**
     * 지정한 길이만큼 문자열을 생성합니다.
     *
     * @param len 생성할 문자열의 길이
     */
    fun generate(len: Int)

    /**
     * generate 메소드를 통해 생성된 문자열을 반환합니다.
     *
     * @return 생성한 문자열
     */
    fun getValue(): String?

    /**
     * generate 메소드를 통해 생성된 문자열을 ByteArray(byte[])로 변환하고 반환합니다.
     *
     * @return 생성된 문자열을 ByteArray(byte[])로 변환한 데이터
     */
    fun getBytes(): ByteArray?
}