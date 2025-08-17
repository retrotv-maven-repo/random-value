package dev.retrotv.random.generator;

import java.util.Random;

/**
 * 무작위 실수 데이터 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 1.0.0
 */
public class DoubleGenerator extends RandomGenerator<Double> {
    private static final int DEFAULT_LENGTH = 16;
    private int length = DEFAULT_LENGTH;

    /**
     * 기본 생성자
     *
     * @param random Random 객체
     */
    public DoubleGenerator(Random random) {
        super(random);
    }

    /**
     * 생성할 실수의 소수점 이하 자리수를 설정합니다.
     *
     * @param length 생성할 실수의 소수점 이하 자리수
     */
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public Double generate() {
        data = Math.round(random.nextDouble() * Math.pow(10.0, length)) / Math.pow(10.0, length);
        return data;
    }
}
