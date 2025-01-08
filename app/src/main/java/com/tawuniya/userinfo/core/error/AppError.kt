package com.tawuniya.userinfo.core.error

import timber.log.Timber

sealed class AppError {
    var exception: Throwable? = GeneralException()
    var extraData: Any? = null
    var message: String? = null
    var logMessage: String? = null
    var logTag: String = "App Error =>"
    var logPriority: ErrorLogPriority = ErrorLogPriority.ERROR

    fun logError() {
        val message = "$logTag \n" +
                "Message: $message \n" +
                "Log Message: $logMessage\n" +
                "Extra Data: $extraData\n"
        Timber.log(logPriority.level, exception, message)
    }


    class E(
        exception: Exception,
        message: String? = null,
        logMessage: String? = null,
        logTag: String? = null,
        extraData: Any? = null,
    ) : AppError() {
        init {
            this.logPriority = ErrorLogPriority.ERROR
            this.message = message
            this.logMessage = logMessage
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }

    class W(
        exception: Exception,
        message: String? = null,
        logMessage: String? = null,
        logTag: String? = null,
        extraData: Any? = null,
    ) : AppError() {
        init {
            this.logPriority = ErrorLogPriority.WARN
            this.message = message
            this.logMessage = logMessage
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }

    class I(
        exception: Exception,
        message: String? = null,
        logMessage: String? = null,
        logTag: String? = null,
        extraData: Any? = null,
    ) : AppError() {
        init {
            this.logPriority = ErrorLogPriority.INFO
            this.message = message
            this.logMessage = logMessage
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }
}