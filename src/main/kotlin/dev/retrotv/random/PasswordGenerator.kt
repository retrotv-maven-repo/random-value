package dev.retrotv.random

import dev.retrotv.random.enums.SecurityStrength
import java.util.*

/**
 * 무작위 패스워드 생성을 위한 클래스 입니다.
 * @see SecurityStrength enum을 통해 생성될 패스워드의 강도를 설정할 수 있습니다.
 *
 * @author yjj8353
 * @since 1.0.0
 * @property securityStrength 생성할 패스워드 보안 강도
 * @property random 무작위 데이터 생성을 위한 Random 객체
 * @constructor 빈 PasswordGenerator 클래스 생성
 */
class PasswordGenerator(securityStrength: SecurityStrength, random: Random)
    : StringGenerator(securityStrength, random)