package com.github.projektmagma.magmaapp.home.data

import com.github.projektmagma.magmaapp.core.data.DataError
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException

inline fun safeFirebaseCall(tryAdd: () -> Unit): Result<Unit, DataError> {
    return try {
        tryAdd()
        Result.Success(Unit)
    } catch (e: FirebaseNetworkException) {
        Result.Error(DataError.NetworkError.NETWORK_ERROR)
    } catch (e: FirebaseTooManyRequestsException) {
        Result.Error(DataError.NetworkError.TOO_MANY_REQUESTS)
    } catch (e: FirebaseException) {
        Result.Error(DataError.NetworkError.SERVER_ERROR)
    } catch (e: Exception) {
        Result.Error(DataError.NetworkError.UNKNOWN_ERROR)
    }
}
