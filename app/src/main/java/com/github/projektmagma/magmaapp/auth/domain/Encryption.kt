package com.github.projektmagma.magmaapp.auth.domain

interface Encryption {
    fun encrypt(password: String, algorithm: String): String
    fun decrypt(password: String, algorithm: String): String
}