package com.tawuniya.userinfo.core.api_services

import com.tawuniya.userinfo.features.home.data.model.response.UserInfoResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("users")
    suspend fun getUserInfo() : Response<List<UserInfoResponseDto>>
}