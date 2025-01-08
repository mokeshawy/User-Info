package com.tawuniya.userinfo.features.home.domain.model.state

import com.tawuniya.userinfo.core.bases.base_viewmodel.ViewState
import com.tawuniya.userinfo.core.error.AppError
import com.tawuniya.userinfo.features.home.domain.model.ui.UserInfoUiModel

data class UserInfoUiState(
    val isLoading: Boolean = false,
    val error: AppError? = null,
    val userInfoUiModelList: List<UserInfoUiModel>? = null
) : ViewState