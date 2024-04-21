package dev.retrotv.random

import java.util.Random

/**
 * 범용적으로 사용할 수 있는 무작위 값 생성 클래스 입니다.
 * property를 통해 입력받은 문자 그룹의 문자들을 토대로 무작위 값을 생성합니다.
 * 기본적으로 모든 문자 그룹에서 최소 한글자 이상 보장하는 isAllCharGroupLeastOne 옵션과,
 * 모든 문자 그룹에서 동일한 확률로 선택되도록 하는 isEqualDistribution 옵션이 활성화 됩니다.
 *
 * @property charGroup 문자 그룹의 집합
 * @constructor property를 통해 입력받은 문자 그룹과 해당 문자 그룹의 문자 개수의 최소공배수 값을 전역변수로 설정한 RandomStringGenerator 객체를 생성합니다.
 */
abstract class RandomStringGenerator: RandomGenerator<String> {
    protected lateinit var generatedValue: String

    abstract override fun generate(len: Int, random: Random)

    override fun getValue(): String {
        return generatedValue
    }
}
