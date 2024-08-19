package dev.retrotv.random

import dev.retrotv.random.enums.SecurityStrength
import java.security.SecureRandom
import java.util.Random

class PINGenerator(random: Random)
    : StringGenerator(SecurityStrength.ONLY_NUMBER, random)
