package com.tawuniya.userinfo.core.error

data class AppException(val appError: AppError) : Exception()