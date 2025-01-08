package com.tawuniya.userinfo.core.state

import com.tawuniya.userinfo.core.error.AppError


sealed class State<T> {

    class Loading<T> : State<T>()
    data class Success<T>(val data: T? = null, val error: AppError? = null) : State<T>()
    data class Error<T>(val error: AppError) : State<T>()


    companion object {
        fun <E> Exception.getError(message: String, logTag: String? = null) =
            Error<E>(AppError.E(exception = this, message = message, logTag = logTag))

        fun <E> Exception.getInfo() = Error<E>(AppError.I(exception = this))
        fun <E> Exception.getWarning() = Error<E>(AppError.W(exception = this))
    }
}