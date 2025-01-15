package com.github.projektmagma.magmaapp.auth.data

import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.util.Error

sealed interface DataError : Error {
    enum class NetworkError(override val messageId: Int) : DataError {
        EMAIL_TAKEN(R.string.error_email_taken),
        SERVER_ERROR(R.string.error_server_error),
        UNKNOWN_ERROR(R.string.error_unknown_error),
        INVALID_CREDENTIALS(R.string.error_invalid_credentials)
    }
}