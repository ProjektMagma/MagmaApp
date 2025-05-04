package com.github.projektmagma.magmaapp.home.data

import android.util.Log
import com.github.projektmagma.magmaapp.core.data.DataError
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout

suspend inline fun <Notebook> safeFirebaseCall(crossinline tryAdd: suspend () -> Notebook): Result<Notebook, DataError> {
    return try {
        withTimeout(5000) {
            Result.Success(tryAdd())
        }

    } catch (e: TimeoutCancellationException) {
        Result.Error(DataError.NetworkError.TIMEOUT)
    } catch (e: FirebaseNetworkException) {
        Result.Error(DataError.NetworkError.NETWORK_ERROR)
    } catch (e: FirebaseTooManyRequestsException) {
        Result.Error(DataError.NetworkError.TOO_MANY_REQUESTS)
    } catch (e: FirebaseException) {
        Result.Error(DataError.NetworkError.SERVER_ERROR)
    } catch (e: Exception) {
        Log.e("FirebaseError", e.message.toString())
        Result.Error(DataError.NetworkError.UNKNOWN_ERROR)
    }
}
