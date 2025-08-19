package dev.retrotv.random.generator;

import dev.retrotv.random.enums.UUIDVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UUIDGeneratorTest {

    @DisplayName("UUID V1 테스트")
    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void test_uuidV1Generator() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        uuidGenerator.setVersion(UUIDVersion.V1);
        Set<String> uuidV1List = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uuidV1List.add(uuidGenerator.generate().toString());
        }

        assertEquals(100, uuidV1List.size());
    }

    @Test
    @DisplayName("UUID V3 테스트")
    void test_uuidV3Generator() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        uuidGenerator.setVersion(UUIDVersion.V3);
        uuidGenerator.setName("RetroTV");
        Set<String> uuidV3List = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uuidV3List.add(uuidGenerator.generate().toString());
        }

        assertEquals(1, uuidV3List.size(), "V3 UUIDs should be the same for the same name");
    }

    @DisplayName("UUID V4 테스트")
    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void test_uuidV4Generator() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        Set<String> uuidV4List = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uuidV4List.add(uuidGenerator.generate().toString());
        }

        assertEquals(100, uuidV4List.size());

        uuidGenerator.setVersion(UUIDVersion.V4);
        uuidV4List.clear();
        for (int i = 0; i < 100; i++) {
            uuidV4List.add(uuidGenerator.generate().toString());
        }

        assertEquals(100, uuidV4List.size());
    }

    @Test
    @DisplayName("UUID V5 테스트")
    void test_uuidV5Generator() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        uuidGenerator.setVersion(UUIDVersion.V5);
        uuidGenerator.setName("RetroTV");
        Set<String> uuidV5List = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uuidV5List.add(uuidGenerator.generate().toString());
        }

        assertEquals(1, uuidV5List.size(), "V5 UUIDs should be the same for the same name");
    }

    @DisplayName("UUID V6 테스트")
    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void test_uuidV6Generator() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        uuidGenerator.setVersion(UUIDVersion.V6);
        Set<String> uuidV6List = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uuidV6List.add(uuidGenerator.generate().toString());
        }

        assertEquals(100, uuidV6List.size());
    }

    @DisplayName("UUID V7 테스트")
    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void test_uuidV7Generator() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        uuidGenerator.setVersion(UUIDVersion.V7);
        Set<String> uuidV7List = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            uuidV7List.add(uuidGenerator.generate().toString());
        }

        assertEquals(100, uuidV7List.size());
    }
}
