package dev.retrotv.random.generator;

import dev.retrotv.data.utils.NumberUtils;
import dev.retrotv.data.utils.StringUtils;
import dev.retrotv.random.enums.SecurityStrength;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 범용적으로 사용할 수 있는 무작위 문자열 값 생성 추상 클래스 입니다.
 * 기본적으로 모든 문자 그룹에서 최소 한글자 이상 보장하는 isAllCharGroupLeastOne 옵션과,
 * 모든 문자 그룹에서 동일한 확률로 선택되도록 하는 isEqualDistribution 옵션이 활성화 됩니다.
 *
 * @author  yjj8353
 * @since   2.0.0
 */
public abstract class StringGenerator extends RandomGenerator<String> {
    private static final Logger log = LoggerFactory.getLogger(StringGenerator.class);

    private static final int DEFAULT_LENGTH = 16;
    private static final char[] SMALL_LETTERS = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    private static final char[] CAPITAL_LETTERS = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    private static final char[] NUMBERS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private static final char[] SPECIAL_CHARACTERS = {
        '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':',
        ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'
    };

    private int length = DEFAULT_LENGTH;

    // 모든 CharGroup에서 각각, 최소 하나의 값을 보장할지 여부
    private boolean isAllCharGroupLeastOne = true;

    // 모든 CharGroup에서 동일한 확률로 값이 선택될지 여부
    private boolean isEqualDistribution = true;
    private List<char[]> allCharGroup;

    // 모든 CharGroup에서 각각, 최소 하나의 값을 보장하기 위한 최소공배수
    private int allCharGroupLeastCommonMultiple;
    private final SecurityStrength securityStrength;

    /**
     * 기본 생성자
     *
     * @author  yjj8353
     * @since   2.0.0
     * @param random Random 객체
     * @param securityStrength 생성할 문자열의 보안 강도 수준
     */
    StringGenerator(Random random, SecurityStrength securityStrength) {
        super(random);
        this.securityStrength = securityStrength;

        switch (securityStrength) {
            case ONLY_NUMBER:
                setAllCharGroupInfo(NUMBERS);
                break;

            case LOW:
                setAllCharGroupInfo(SMALL_LETTERS, NUMBERS);
                break;

            case MIDDLE:
                setAllCharGroupInfo(SMALL_LETTERS, CAPITAL_LETTERS, NUMBERS);
                break;

            case HIGH:
                setAllCharGroupInfo(SMALL_LETTERS, CAPITAL_LETTERS, NUMBERS, SPECIAL_CHARACTERS);
        }
    }

    @Override
    public String generate() {
        generateValue();
        return data;
    }

    /**
     * 생성할 무작위 문자열의 길이를 설정합니다.
     * 길이는 0보다 큰 정수여야 합니다.
     *
     * @author yjj8353
     * @since 2.0.0
     * @param length 생성할 무작위 문자열의 길이
     */
    public void setLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("생성할 무작위 값 길이 len은 0보다 작을 수 없습니다.");
        }
        this.length = length;
    }

    /**
     * 모든 CharGroup에서 각각, 최소 하나의 값을 보장하도록 설정합니다.
     *
     * @author yjj8353
     * @since 2.0.0
     */
    public void enableAllCharGroupLeastOne() {
        isAllCharGroupLeastOne = true;
    }

    /**
     * 모든 CharGroup에서 각각, 최소 하나의 값을 보장하지 않아도 상관 없도록 설정합니다.
     *
     * @author yjj8353
     * @since 2.0.0
     */
    public void disableAllCharGroupLeastOne() {
        isAllCharGroupLeastOne = false;
    }

    /**
     * 모든 CharGroup에서 동일한 확률로 값이 선택되도록 설정합니다.
     *
     * @author yjj8353
     * @since 2.0.0
     */
    public void enableEqualDistribution() {
        isEqualDistribution = true;
    }

    /**
     * 모든 CharGroup에서 동일하지 않은 확률로 값이 선택되지 않아도 상관 없도록 설정합니다.
     *
     * @author yjj8353
     * @since 2.0.0
     */
    public void disableEqualDistribution() {
        isEqualDistribution = false;
    }

    // 전역변수인 allCharGroup 및 allCharGroupLeastCommonMultiple 값을 설정합니다.
    private void setAllCharGroupInfo(char[] ...charGroups) {
        allCharGroup = Arrays.asList(charGroups);
        allCharGroupLeastCommonMultiple = charGroups.length == 1 ? charGroups[0].length : NumberUtils.leastCommonMultiple(
            Arrays.stream(charGroups).mapToInt(charArray -> charArray.length).toArray()
        );
    }

    // 무작위 문자열을 생성하고 전역변수인 data에 저장힙니다.
    private void generateValue() {
        // 문자 그룹의 개수가 생성할 무작위 문자열의 길이보다 클 경우 예외 발생
        if (isAllCharGroupLeastOne && allCharGroup.size() > length) {
            String message = "len 값이 allCharGroup.size 보다 작습니다.\n"
                           + "생성할 문자열 길이의 값은 모든 문자 그룹의 개수보다 크거나 같아야 합니다.\n"
                           + "이 오류를 무시하려면 generate 메소드 실행 전에 disableAllCharGroupLeastOne 메소드를 실행해\n"
                           + "모든 문자 그룹에서 최소 한글자 이상 보장하는 옵션을 비활성화 하십시오.";
            throw new IllegalArgumentException(message);
        }

        char[] fullChars = getFullChars();
        char[] allLeastOneChars = isAllCharGroupLeastOne ? getAllCharGroupLeastOne() : null;

        char[] ca = new char[length];
        int i = (allLeastOneChars != null) ? allLeastOneChars.length : 0;
        while (i < length) {
            int randomIndex = random.nextInt(fullChars.length);
            ca[i] = fullChars[randomIndex];
            i++;
        }

        log.trace(
              "\n==================== StringGenerator 설정 ====================\n"
            + "| 보안강도 수준: {}\n"
            + "| 각각의 문자 그룹에서 최소 한 글자 이상 보장: {}\n"
            + "| 각각의 문자 그룹에서 선택 시 동일한 확률 보장: {}\n"
            + "| 모든 문자의 개수: {}\n"
            + "============================================================="
            , this.securityStrength.getLabel()
            , this.isAllCharGroupLeastOne
            , this.isEqualDistribution
            , fullChars.length
        );

        if (isAllCharGroupLeastOne) {
            System.arraycopy(
                Objects.requireNonNull(allLeastOneChars),
                0,
                ca,
                0,
                allLeastOneChars.length
            );
            data = StringUtils.scrambleChars(new String(ca));
        } else {
            data = new String(ca);
        }
    }

    // 모든 CharGroup을 묶어, 하나의 char[]로 변환해 반환합니다.
    private char[] getFullChars() {
        char[] fullChars = isEqualDistribution
            ? new char[allCharGroupLeastCommonMultiple * allCharGroup.size()]
            : new char[allCharGroup.stream().mapToInt(group -> group.length).sum()];
        int destPos = 0;

        for (char[] group : allCharGroup) {
            int len = isEqualDistribution ? allCharGroupLeastCommonMultiple / group.length : 1;
            for (int i = 0; i < len; i++) {
                System.arraycopy(group, 0, fullChars, destPos, group.length);
                destPos += group.length;
            }
        }

        return fullChars;
    }

    /*
     * 모든 CharGroup에서 각각 하나의 문자를 무작위로 뽑아 반환합니다.
     * 예: 소문자/숫자 CharGroup이 존재하면, 소문자 한 글자와 숫자 한 글자를 반환.
     */
    private char[] getAllCharGroupLeastOne() {
        char[] acglo = new char[allCharGroup.size()];
        for (int i = 0; i < allCharGroup.size(); i++) {
            char[] group = allCharGroup.get(i);
            int randomIndex = random.nextInt(group.length);
            acglo[i] = group[randomIndex];
        }
        return acglo;
    }
}
