package com.github.projektmagma.magmaapp.auth.domain.use_cases

import com.github.projektmagma.magmaapp.auth.domain.Encryption

class EncryptUseCase(
    private val encryption: Encryption
) {
    fun execute(password: String, algorithm: String = "MD5"): String {
        return encryption.encrypt(password, algorithm)
    }
}