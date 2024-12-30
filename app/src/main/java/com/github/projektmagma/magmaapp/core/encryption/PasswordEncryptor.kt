package com.github.projektmagma.magmaapp.core.encryption

import java.security.MessageDigest

class PasswordEncryptor {

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        fun encrypt(password: String, algorithm: String = "MD5"): String {

            val md = MessageDigest.getInstance(algorithm)
            val digest = md.digest(password.toByteArray())
            return digest.toHexString()

        }
    }
}