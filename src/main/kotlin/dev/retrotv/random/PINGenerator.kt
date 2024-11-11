package dev.retrotv.random

import dev.retrotv.random.enums.SecurityStrength
import java.util.Random

/**
 * 무작위 PIN 생성을 위한 클래스 입니다.
 *
 * @author yjj8353
 * @since 1.0.0
 * @property random 무작위 데이터 생성을 위한 Random 객체
 */
class PINGenerator(random: Random)
    : StringGenerator(SecurityStrength.ONLY_NUMBER, random)
