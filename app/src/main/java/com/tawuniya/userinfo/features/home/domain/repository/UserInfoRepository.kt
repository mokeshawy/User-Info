package com.tawuniya.userinfo.features.home.domain.repository

import com.tawuniya.userinfo.core.state.State
import com.tawuniya.userinfo.features.home.data.model.response.UserInfoResponseDto
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {

    suspend fun getUserInfo(): Flow<State<List<UserInfoResponseDto>>>
}