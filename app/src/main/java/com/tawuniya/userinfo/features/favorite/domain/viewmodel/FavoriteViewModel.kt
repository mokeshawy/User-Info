package com.tawuniya.userinfo.features.favorite.domain.viewmodel

import com.tawuniya.userinfo.core.bases.base_viewmodel.BaseViewModel
import com.tawuniya.userinfo.core.extensions.viewModelScope
import com.tawuniya.userinfo.features.common.domain.events.FavoriteIntent
import com.tawuniya.userinfo.features.common.domain.model.state.FavoriteUiState
import com.tawuniya.userinfo.features.common.domain.repository.AddFavoriteUserRepository
import com.tawuniya.userinfo.features.common.domain.repository.UnFavoriteUserRepository
import com.tawuniya.userinfo.features.common.domain.usecase.ReadFavoriteUserinfoUseCase
import com.tawuniya.userinfo.features.home.domain.model.ui.UserInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val readFavoriteUserinfoUseCase: ReadFavoriteUserinfoUseCase,
    private val addFavoriteUserRepository: AddFavoriteUserRepository,
    private val unFavoriteUserRepository: UnFavoriteUserRepository
) : BaseViewModel<FavoriteIntent, FavoriteUiState>(FavoriteUiState()) {


    init {
        sendGetUserInfoEntitiesIntent()
    }

    fun handleFavoriteUnFavoriteItem(isFavorite: Boolean, userInfoUiModel: UserInfoUiModel) {
        when (isFavorite) {
            true -> sendAddUserInfoToFavoriteIntent(userInfoUiModel = userInfoUiModel)
            false -> sendUnFavoriteIntent(id = userInfoUiModel.id)
        }
    }

    fun sendGetUserInfoEntitiesIntent() = sendIntent(FavoriteIntent.GetUserInfoEntities)

    private fun sendAddUserInfoToFavoriteIntent(
        userInfoUiModel: UserInfoUiModel,
    ) = sendIntent(
        FavoriteIntent.AddToFavorite(
            userInfoUiModel = userInfoUiModel, isFavorite = true
        )
    )

    fun sendUnFavoriteIntent(id: Int) = sendIntent(FavoriteIntent.UnFavorite(id = id))

    override fun processIntent(intent: FavoriteIntent) {
        when (intent) {
            is FavoriteIntent.AddToFavorite -> reduceAddUserInfoToFavoriteState(
                userInfoUiModel = intent.userInfoUiModel,
                isFavorite = intent.isFavorite
            )

            FavoriteIntent.GetUserInfoEntities -> reduceGetUserInfoEntitiesState()
            is FavoriteIntent.UnFavorite -> reduceUnFavoriteUserInfoState(intent.id)
        }
    }

    private fun reduceGetUserInfoEntitiesState() = viewModelScope {
        updateStateOf { copy(isLoading = true) }
        readFavoriteUserinfoUseCase().collect {
            if (it.isNotEmpty()) {
                updateStateOf { copy(isLoading = false, userInfoEntitiesUiModel = it) }
            } else {
                updateStateOf {
                    copy(
                        isLoading = false,
                        errorMessage = "There is no favorite here",
                        userInfoEntitiesUiModel = null
                    )
                }
            }
        }
    }


    private fun reduceAddUserInfoToFavoriteState(
        userInfoUiModel: UserInfoUiModel,
        isFavorite: Boolean
    ) = viewModelScope {
        addFavoriteUserRepository.addFavoriteUserInfo(
            userInfoUiModel = userInfoUiModel, isFavorite = isFavorite
        )
    }

    private fun reduceUnFavoriteUserInfoState(id: Int) = viewModelScope {
        unFavoriteUserRepository.unFavoriteUserInfo(id = id)
    }
}