package com.github.projektmagma.magmaapp.auth.domain

interface Validation {
    fun isNullOrEmpty(email: String, password: String): Boolean
    fun testRegex(email: String, regex: Regex): Boolean
}