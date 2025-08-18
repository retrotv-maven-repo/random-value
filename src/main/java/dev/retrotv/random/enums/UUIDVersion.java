package dev.retrotv.random.enums;

/**
 * UUID 버전을 표현하기 위한 열거형 클래스입니다.
 *
 * @author  yjj8353
 * @since   2.0.0
 */
public enum UUIDVersion {
    V1("v1", "시간 기반 UUID"),
    V3("v3", "네임스페이스 기반 UUID (MD5 해시)"),
    V4("v4", "무작위 UUID"),
    V5("v5", "네임스페이스 기반 UUID (SHA-1 해시)"),
    V6("v6", "시간 기반 UUID (버전 6)"),
    V7("v7", "시간 기반 UUID (버전 7)");

    private final String label;
    private final String explain;

    UUIDVersion(String label, String explain) {
        this.label = label;
        this.explain = explain;
    }

    /**
     * 해당 enum의 label 값을 반환합니다.
     *
     * @return label 값
     */
    public String getLabel() {
        return label;
    }

    /**
     * 해당 enum의 explain 값을 반환합니다.
     *
     * @return explain 값
     */
    public String getExplain() {
        return explain;
    }
}
