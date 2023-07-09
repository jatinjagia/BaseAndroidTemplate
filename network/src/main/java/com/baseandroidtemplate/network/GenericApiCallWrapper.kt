package com.baseandroidtemplate.network

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): ResultWrapper<Response<T>> {
    return try {
        val response = apiCall.invoke()
        val responseCode = response.code()
        if (response.isSuccessful && (responseCode in 200..299))
            ResultWrapper.Success(response)
        else if(responseCode in 401..403){
            ResultWrapper.UnauthorisedError(
                responseCode,
                convertErrorBody(response.errorBody()!!.string())
            )
        } else {
            ResultWrapper.GenericError(
                responseCode,
                convertErrorBody(response.errorBody()!!.string())
            )
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultWrapper.NetworkError
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable)
                ResultWrapper.GenericError(code, errorResponse)
            }
            is JsonDataException -> {
                val code = throwable.hashCode()
                val errorBody = ErrorResponse("200",throwable.message!!,"0")
                ResultWrapper.GenericError(code, errorBody)
            }
            else -> {
                ResultWrapper.GenericError(null, null)
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}

private fun convertErrorBody(string: String): ErrorResponse? {
    val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
    return moshiAdapter.fromJson(string)
}