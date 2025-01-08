package com.tawuniya.userinfo.core.error

import androidx.annotation.Keep


open class AppErrorException(message: String? = null, cause: Throwable? = null) :
    RuntimeException(message, cause)

@Keep
class GeneralException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)

@Keep
class IoException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)

@Keep
class ResponseException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)

@Keep
class ResponseUnAuthorizedException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


@Keep
class FiledToOpenWhatsAppException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


@Keep
class FiledToOpenGoogleMapsException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


