package dev.retrotv.random.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UUIDGeneratorTest {

    @DisplayName("중복 테스트")
    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void test_uuidGenerator() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        Set<String> uuidList = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uuidList.add(uuidGenerator.generate().toString());
        }

        assertEquals(100, uuidList.size());
    }
}
