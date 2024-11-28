package dev.retrotv.random

import java.util.UUID

/**
 * UUID 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 1.0.0
 */
class UUIDGenerator {

    /**
     * 문자열로 된 UUID를 생성하고 반환합니다.
     *
     * @return 생성된 UUID 문자열
     */
    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}