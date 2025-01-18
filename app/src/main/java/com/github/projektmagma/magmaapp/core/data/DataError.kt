package com.github.projektmagma.magmaapp.core.data

import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.util.Error

sealed interface DataError : Error {
    enum class NetworkError(override val messageId: Int) : DataError {
        EMAIL_TAKEN(R.string.error_email_taken),
        SERVER_ERROR(R.string.error_server_error),
        NETWORK_ERROR(R.string.error_network_error),
        TOO_MANY_REQUESTS(R.string.error_too_many_requests),
        UNKNOWN_ERROR(R.string.error_unknown_error),
        INVALID_CREDENTIALS(R.string.error_invalid_credentials)
    }
}