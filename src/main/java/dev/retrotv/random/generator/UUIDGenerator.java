package dev.retrotv.random.generator;

import com.github.f4b6a3.uuid.UuidCreator;
import dev.retrotv.random.enums.UUIDVersion;

import java.util.UUID;

/**
 * UUID 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 2.0.0
 */
public class UUIDGenerator extends RandomGenerator<UUID> {
    private static final UUIDVersion DEFAULT_UUID_VERSION = UUIDVersion.V4;
    private UUIDVersion version = DEFAULT_UUID_VERSION;
    private String name = "";

    /**
     * 기본 생성자
     *
     * @author yjj8353
     * @since 2.0.0
     */
    public UUIDGenerator() {
        super();
    }

    /**
     * UUID 생성 시, 사용할 버전을 지정하는 생성자
     *
     * @author yjj8353
     * @since 2.0.0
     * @param version UUID 버전
     */
    public void setVersion(UUIDVersion version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public UUID generate() {
        UUID uuid;

        switch (version) {
            case V1:
                uuid = UuidCreator.getTimeBased();
                break;
            case V3:
                uuid = UuidCreator.getNameBasedMd5(name);
                break;
            case V5:
                uuid = UuidCreator.getNameBasedSha1(name);
                break;
            case V6:
                uuid = UuidCreator.getTimeOrdered();
                break;
            case V7:
                uuid = UuidCreator.getTimeOrderedEpoch();
                break;
            case V4:
            default:
                uuid = UuidCreator.getRandomBased();
        }

        return uuid;
    }
}
