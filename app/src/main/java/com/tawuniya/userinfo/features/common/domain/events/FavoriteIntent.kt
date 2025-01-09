package com.tawuniya.userinfo.features.common.domain.events

import com.tawuniya.userinfo.core.bases.base_viewmodel.ViewIntent
import com.tawuniya.userinfo.features.home.domain.model.ui.UserInfoUiModel

sealed class FavoriteIntent : ViewIntent {
    data class AddToFavorite(
        val userInfoUiModel: UserInfoUiModel, val isFavorite: Boolean
    ) : FavoriteIntent()

    data class UnFavorite(val id: Int) : FavoriteIntent()
    data object GetUserInfoEntities : FavoriteIntent()
}