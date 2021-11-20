package com.faztbit.data.source.services.retrofit

import android.accounts.NetworkErrorException
import android.util.Log
import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

abstract class BaseClient {
    protected suspend inline fun <T> callService(crossinline retrofitCall: suspend () -> Response<T>): Either<Failure, T> {
        return when (true) {
            true -> {
                try {
                    withContext(Dispatchers.IO) {
                        val response = retrofitCall.invoke()
                        if (response.isSuccessful && response.body() != null) {
                            return@withContext Either.Success(response.body()!!)
                        } else {
                            Log.e("EDMUNDO", response.message())
                            Log.e("EDMUNDO2", response.toString())
                            Log.e("EDMUNDO3", response.errorBody().toString())
                            return@withContext Either.Error(
                                getErrorMessageFromServer(
                                    Gson().fromJson(
                                        response.errorBody()?.charStream(),
                                        BaseResponseError::class.java
                                    )
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    Log.e("error", "$e and  ${e.message}")
                    return Either.Error(parseException(e))
                }
            }
            false -> Either.Error(Failure.NoNetworkDetected)
        }
    }

    suspend fun getErrorMessageFromServer(data: BaseResponseError): Failure {
        return if (!data.message.isNullOrEmpty()) {
            return withContext(Dispatchers.IO) {
                return@withContext when (data.codeStatus) {
                    404 -> {
                        Failure.ResourceNotFound()
                    }
                    503 -> {
                        Failure.ServerError(data.codeStatus, data.message)
                    }
                    else -> {
                        Failure.ErrorServerNotMapped(data.codeStatus ?: 500, data.message)
                    }
                }
            }
        } else {
            Failure.ErrorNotMapped()
        }
    }

    fun parseException(throwable: Throwable): Failure {
        return when (throwable) {
            is NetworkErrorException -> Failure.NoNetworkDetected
            is SocketTimeoutException -> Failure.TimeOut
            is SSLException -> Failure.NetworkConnectionLostSuddenly
            is SSLHandshakeException -> Failure.SSLError
            else -> Failure.ServiceUncaughtFailure(
                throwable.message ?: "Service response doesn't match with response object."
            )
        }
    }
}