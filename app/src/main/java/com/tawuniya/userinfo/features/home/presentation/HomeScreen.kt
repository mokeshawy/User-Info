package com.tawuniya.userinfo.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tawuniya.userinfo.R
import com.tawuniya.userinfo.composable.GeneralError
import com.tawuniya.userinfo.composable.MainTopBar
import com.tawuniya.userinfo.features.common.components.UserInfoItem
import com.tawuniya.userinfo.features.common.domain.model.ui.UserInfoEntitiesUiModel
import com.tawuniya.userinfo.features.favorite.domain.viewmodel.FavoriteViewModel
import com.tawuniya.userinfo.features.home.domain.model.ui.UserInfoUiModel
import com.tawuniya.userinfo.features.home.domain.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onNavigateToFavorite: () -> Unit
) {
    val userInfoUiState = viewModel.uiState
    val favoriteUiState = favoriteViewModel.uiState

    MainTopBar(
        isShowRightIcon = true,
        rightIcon = R.drawable.ic_vector_archive_book,
        onRightIconClicked = onNavigateToFavorite,
        onRefresh = { viewModel.sendGetUserInfoIntent() },
        isRefreshing = userInfoUiState.isLoading,
        title = R.string.home
    ) {

        if (userInfoUiState.error != null) {
            GeneralError(message = userInfoUiState.error.logMessage, onRefresh = {
                viewModel.sendGetUserInfoIntent()
            })
        }

        if (userInfoUiState.userInfoUiModelList?.isNotEmpty() == true) {
            HomeContent(
                userInfoUiModel = userInfoUiState.userInfoUiModelList,
                userInfoEntitiesUiModel = favoriteUiState.userInfoEntitiesUiModel ?: emptyList(),
                onFavoriteClicked = { isFavorite, userInfoUiModel ->
                    favoriteViewModel.handleFavoriteUnFavoriteItem(
                        isFavorite = isFavorite,
                        userInfoUiModel = userInfoUiModel
                    )
                })
        }
    }
}


@Composable
fun HomeContent(
    userInfoUiModel: List<UserInfoUiModel>,
    userInfoEntitiesUiModel: List<UserInfoEntitiesUiModel>,
    onFavoriteClicked: (Boolean, UserInfoUiModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(userInfoUiModel, key = { it.id }) { item ->
            UserInfoItem(
                email = item.email,
                id = item.id,
                name = item.name,
                phone = item.phone,
                username = item.username,
                website = item.website,
                lat = item.address.geofence.lat.toDouble(),
                lng = item.address.geofence.lng.toDouble(),
                isFavorite = userInfoEntitiesUiModel.filter { item.id == it.id }
                    .map { it.isFavorite }.firstOrNull() ?: false,
                onFavoriteClicked = {
                    onFavoriteClicked(it, item)
                },
                onItemClicked = {}
            )
        }
    }
}