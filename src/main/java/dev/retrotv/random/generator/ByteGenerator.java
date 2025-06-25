package dev.retrotv.random.generator;

import java.util.Random;

/**
 * 무작위 바이트 데이터 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 2.0.0
 */
public class ByteGenerator extends RandomGenerator<byte[]> {
    private static final int DEFAULT_LENGTH = 16;
    private int length = DEFAULT_LENGTH;

    /**
     * 기본 생성자
     *
     * @param random Random 객체
     */
    public ByteGenerator(Random random) {
        super(random);
    }

    /**
     * 생성할 바이트 배열의 길이를 설정합니다.
     *
     * @param length 생성할 바이트 배열의 길이
     */
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public byte[] generate() {
        data = new byte[length];
        random.nextBytes(data);

        return data;
    }
}
