package com.pirksni.leantech.presentation.util.network

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


@Suppress("TooGenericExceptionCaught")
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    moshi: Moshi,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable, moshi)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

@Suppress("SwallowedException", "TooGenericExceptionCaught")
private fun convertErrorBody(throwable: HttpException, moshi: Moshi): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.string()?.let {
            val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
            jsonAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}
