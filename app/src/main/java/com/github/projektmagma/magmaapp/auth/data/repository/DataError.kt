package com.github.projektmagma.magmaapp.auth.data.repository

import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.util.Error

sealed interface DataError : Error {
    enum class NetworkError(override val messageId: Int) : DataError {
        EMAIL_TAKEN(R.string.error_email_taken),
        SERVER_ERROR(R.string.error_server_error),
    }
}