package com.github.projektmagma.magmaapp.auth.data.repository

import com.github.projektmagma.magmaapp.core.util.Error

sealed interface DataError: Error{
    enum class NetworkError: DataError {
        EMAIL_TAKEN,
        SERVER_ERROR,
    }
}