package dev.retrotv.random.generator;

import java.util.Random;

import static dev.retrotv.random.enums.SecurityStrength.ONLY_NUMBER;

/**
 * 무작위 PIN 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 2.0.0
 */
public class PINGenerator extends StringGenerator {

    /**
     * 기본 생성자
     * 기본 생성 길이는 4자리로 별도 설정됩니다.
     *
     * @author  yjj8353
     * @since   2.0.0
     * @param random Random 객체
     */
    PINGenerator(Random random) {
        super(random, ONLY_NUMBER);
        this.setLength(4);
    }
}
