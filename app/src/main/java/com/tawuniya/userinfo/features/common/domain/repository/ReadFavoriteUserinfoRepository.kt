package com.tawuniya.userinfo.features.common.domain.repository

import com.tawuniya.userinfo.features.common.data.model.UserInfoEntities
import kotlinx.coroutines.flow.Flow

interface ReadFavoriteUserinfoRepository {

    suspend fun getFavoriteUser(): Flow<List<UserInfoEntities>>
}