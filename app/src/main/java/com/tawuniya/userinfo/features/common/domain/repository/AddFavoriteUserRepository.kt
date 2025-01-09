package com.tawuniya.userinfo.features.common.domain.repository

import com.tawuniya.userinfo.features.home.domain.model.ui.UserInfoUiModel

interface AddFavoriteUserRepository {
    suspend fun addFavoriteUserInfo(userInfoUiModel: UserInfoUiModel, isFavorite: Boolean)
}