package com.tawuniya.userinfo.features.common.domain.repository

import com.tawuniya.userinfo.features.common.data.model.UserInfoEntities

interface ReadFavoriteUserinfoRepository {

    suspend fun getFavoriteUser(): List<UserInfoEntities>
}