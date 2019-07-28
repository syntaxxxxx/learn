package com.syntax.learn.common.extensions

import com.syntax.learn.R
import com.syntax.learn.data.model.ErrorResponse
import retrofit2.HttpException

fun HttpException.asErrorResponse(): ErrorResponse? {
    if (code() == 500) {
        return ErrorResponse(
            R.string.internal_server_error_message.resString(),
            R.string.internal_server_error_message.resString(),
            R.string.internal_server_error_code.resString()
        )
    }

    return response()?.errorBody()?.string()?.fromJson()
}

fun Throwable.asErrorResponse(): ErrorResponse? {
    val error = this as? HttpException ?: return null
    if (error.code() == 500) {
        return ErrorResponse(
            R.string.internal_server_error_message.resString(),
            R.string.internal_server_error_message.resString(),
            R.string.internal_server_error_code.resString()
        )
    }

    return error.response()?.errorBody()?.string()?.fromJson()
}

fun Throwable.errorMessage(): String {
    if (this is HttpException) {
        return this.asErrorResponse()?.errorMessage ?: this.message ?: ""
    }

    return this.message ?: ""
}