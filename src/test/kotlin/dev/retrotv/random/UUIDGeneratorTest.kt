package dev.retrotv.random

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import java.util.HashSet
import kotlin.test.assertEquals

class UUIDGeneratorTest {

    @DisplayName("중복 테스트")
    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    fun test_duplicate() {
        val uuidGenerator = UUIDGenerator()
        val uuidList = HashSet<String>()
        for (i in 0 until 100) {
            uuidList.add(uuidGenerator.generate())
        }

        assertEquals(100, uuidList.size)
    }
}