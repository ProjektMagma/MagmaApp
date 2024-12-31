package com.github.projektmagma.magmaapp.auth.data

import com.github.projektmagma.magmaapp.auth.domain.Encryption
import java.security.MessageDigest

class PasswordEncryption: Encryption {
    @OptIn(ExperimentalStdlibApi::class)
    override fun encrypt(password: String, algorithm: String): String {
        val md = MessageDigest.getInstance(algorithm)
        val digest = md.digest(password.toByteArray())
        return digest.toHexString()
    }

    override fun decrypt(password: String, algorithm: String): String {
        // TODO jeszcze nie ma ale fajnie jakby bylo
        return "Have not implemented yet"
    }

}