package com.tawuniya.userinfo.features.home.domain.viewmodel

import com.tawuniya.userinfo.core.bases.base_viewmodel.BaseViewModel
import com.tawuniya.userinfo.core.extensions.collectOnFlowState
import com.tawuniya.userinfo.core.extensions.viewModelScope
import com.tawuniya.userinfo.features.home.domain.events.HomeIntent
import com.tawuniya.userinfo.features.home.domain.model.state.UserInfoUiState
import com.tawuniya.userinfo.features.home.domain.usecase.UserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase
) : BaseViewModel<HomeIntent, UserInfoUiState>(UserInfoUiState()) {


    init {
        sendGetUserInfoIntent()
    }

    private fun sendGetUserInfoIntent() = sendIntent(HomeIntent.GetHome)

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.GetHome -> reduceUserinfoUiState()
        }
    }

    private fun reduceUserinfoUiState() = viewModelScope {
        updateStateOf { copy(isLoading = true) }
        userInfoUseCase().collectOnFlowState(
            onError = {
                handleError(it) { updateStateOf { copy(isLoading = false, error = it) } }
            }, onSuccess = {
                updateStateOf {
                    copy(isLoading = false, userInfoUiModelList = getUserInfoUiModelList())
                }
            })
    }

    private fun getUserInfoUiModelList() = userInfoUseCase.getUserInfoUiModelList()
}