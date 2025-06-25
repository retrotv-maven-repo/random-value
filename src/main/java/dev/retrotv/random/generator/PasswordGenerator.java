package dev.retrotv.random.generator;

import dev.retrotv.random.enums.SecurityStrength;

import java.util.Random;

/**
 * 무작위 패스워드 생성을 위한 클래스 입니다.
 * @see SecurityStrength enum을 통해 생성될 패스워드의 강도를 설정할 수 있습니다.
 *
 * @author yjj8353
 * @since 2.0.0
 */
public class PasswordGenerator extends StringGenerator {

    /**
     * 기본 생성자
     *
     * @author  yjj8353
     * @since   2.0.0
     * @param random 무작위 데이터 생성을 위한 Random 객체
     * @param securityStrength 생성할 패스워드 보안 강도
     */
    public PasswordGenerator(Random random, SecurityStrength securityStrength) {
        super(random, securityStrength);
    }
}
