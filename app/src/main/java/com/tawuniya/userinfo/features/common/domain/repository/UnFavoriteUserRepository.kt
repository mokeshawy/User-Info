package com.tawuniya.userinfo.features.common.domain.repository

interface UnFavoriteUserRepository {
    suspend fun unFavoriteUserInfo(id: Int)
}