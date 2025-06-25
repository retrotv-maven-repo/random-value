package dev.retrotv.random.generator;

import java.util.Random;

/**
 * 랜덤한 값 생성을 위한 클래스를 구현하기 위해 상속 받아야하는 추상 클래스 입니다.
 *
 * @author  yjj8353
 * @since   2.0.0
 */
public abstract class RandomGenerator<T> {
    protected Random random;
    protected T data;

    /**
     * 무작위 T 자료형 데이터를 생성하고 반환합니다.
     *
     * @author  yjj8353
     * @since   2.0.0
     * @return 생성된 T 자료형 데이터
     */
    public abstract T generate();

    /**
     * 기본 생성자
     *
     * @param random Random 객체
     */
    RandomGenerator(Random random) {
        this.random = random;
    }

    /**
     * 기본 생성자
     * 이 생성자는 Random 객체를 사용하지 않는 경우에 사용됩니다.
     */
    RandomGenerator() {
        this.random = null;
    }
}
