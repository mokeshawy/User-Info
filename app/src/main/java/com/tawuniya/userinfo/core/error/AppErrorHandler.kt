package com.tawuniya.userinfo.core.error

interface AppErrorHandler {
    fun handleError(error: AppError, callback: AppError.() -> Unit = {})
}