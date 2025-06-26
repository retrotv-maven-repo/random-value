package dev.retrotv.random.generator;

import dev.retrotv.data.utils.ByteUtils;

import java.util.Random;

public class HEXGenerator extends RandomGenerator<String> {
    private static final int DEFAULT_LENGTH = 16;
    private int length = DEFAULT_LENGTH;

    /**
     * 기본 생성자
     *
     * @author  yjj8353
     * @since   2.0.0
     * @param random 무작위 데이터 생성을 위한 Random 객체
     */
    public HEXGenerator(Random random) {
        this.random = random;
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
    public String generate() {
        byte[] randomData = new byte[length];
        random.nextBytes(randomData);
        data = ByteUtils.toHexString(randomData);

        return data;
    }
}
