package dev.retrotv.random.generator;

import java.util.UUID;

/**
 * UUID 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 2.0.0
 */
public class UUIDGenerator extends RandomGenerator<UUID> {

    /**
     * 기본 생성자
     */
    public UUIDGenerator() {
        super();
    }

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
