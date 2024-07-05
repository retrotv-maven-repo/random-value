package dev.retrotv.random

import dev.retrotv.random.enums.RandomValueType
import dev.retrotv.random.enums.SecurityStrength
import java.security.SecureRandom
import java.util.Random

class PINGenerator(random: Random)
    : StringGenerator(RandomValueType.PIN, SecurityStrength.ONLY_NUMBER, SecureRandom())
