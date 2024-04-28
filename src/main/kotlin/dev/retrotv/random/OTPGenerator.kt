package dev.retrotv.random

import dev.retrotv.random.enums.RandomValueType
import dev.retrotv.random.enums.SecurityStrength
import java.security.SecureRandom
import java.util.Random

class OTPGenerator(random: Random)
    : RandomStringGenerator(RandomValueType.OTP, SecurityStrength.ONLY_NUMBER, SecureRandom())
