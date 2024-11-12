package dev.retrotv.random.enums

/**
 * 보안 강도를 표현하기 위한 열거형 클래스 입니다.
 *
 * @author  yjj8353
 * @since   1.0.0
 */
enum class SecurityStrength(private val label: String, private val explain: String) {
      ONLY_NUMBER("only number", "숫자만 사용합니다.")
    , LOW("low", "소문자와 숫자만을 사용합니다.")
    , MIDDLE("middle", "대문자, 소문자 그리고 숫자를 사용합니다.")
    , HIGH("high", "대문자, 소문자, 숫자 그리고 특수문자를 사용합니다.")
    ;

    /**
     * 해당 enum의 label 값을 반환합니다.
     *
     * @return label 값
     */
    fun label(): String {
        return label
    }

    /**
     * 해당 enum의 explain 값을 반환합니다.
     *
     * @return explain 값
     */
    fun explain(): String {
        return explain
    }
}
