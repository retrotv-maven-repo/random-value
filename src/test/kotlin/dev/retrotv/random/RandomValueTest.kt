package dev.retrotv.random

import dev.retrotv.random.enums.SecurityStrength
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class RandomValueTest {

    @Test
    @DisplayName("낮은 강도의 Random Value 생성")
    fun test_generateRandomValueLowStrength() {
        val rv = RandomValue(securityStrength = SecurityStrength.LOW)
        rv.generate()

        println(rv.getValue())
    }

    @Test
    @DisplayName("보통 강도의 Random Value 생성")
    fun test_generateRandomValueMiddleStrength() {
        val rv = RandomValue(securityStrength = SecurityStrength.MIDDLE)
        rv.generate()

        println(rv.getValue())
    }

    @Test
    @DisplayName("높은 강도의 Random Value 생성")
    fun test_generateRandomValueHighStrength() {
        val rv = RandomValue(securityStrength = SecurityStrength.HIGH)
        rv.generate()

        println(rv.getValue())
    }
}