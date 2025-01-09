package com.tawuniya.userinfo.features.common.domain.model.state

import com.tawuniya.userinfo.core.bases.base_viewmodel.ViewState
import com.tawuniya.userinfo.features.common.domain.model.ui.UserInfoEntitiesUiModel

data class FavoriteUiState(
    val isLoading : Boolean = false,
    val errorMessage : String? = null,
    val isAddFavoriteSuccess : Boolean = false,
    val isUnFavoriteSuccess : Boolean = false,
    val userInfoEntitiesUiModel: List<UserInfoEntitiesUiModel>? = null
) : ViewState
