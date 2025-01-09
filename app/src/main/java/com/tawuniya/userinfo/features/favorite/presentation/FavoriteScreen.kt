package com.tawuniya.userinfo.features.favorite.presentation

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


@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {

    val favoriteUiState = viewModel.uiState

    MainTopBar(
        isShowLeftIcon = true,
        onBackHandler = onBackClicked,
        onLeftIconClicked = onBackClicked,
        onRefresh = { viewModel.sendGetUserInfoEntitiesIntent() },
        isRefreshing = favoriteUiState.isLoading,
        title = R.string.favorite
    ) {

        favoriteUiState.errorMessage?.let {
            GeneralError(message = it, onRefresh = { viewModel.sendGetUserInfoEntitiesIntent() })
        }

        favoriteUiState.userInfoEntitiesUiModel?.let {
            FavoriteContent(userInfoEntitiesUiModel = it,
                onFavoriteClicked = { isFavorite, id ->
                if (!isFavorite) {
                    viewModel.sendUnFavoriteIntent(id = id)
                }
            })
        }
    }
}

@Composable
fun FavoriteContent(
    userInfoEntitiesUiModel: List<UserInfoEntitiesUiModel>,
    onFavoriteClicked: (Boolean, Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(userInfoEntitiesUiModel, key = { it.id }) { item ->
            UserInfoItem(
                email = item.email,
                id = item.id,
                name = item.name,
                phone = item.phone,
                username = item.username,
                website = item.website,
                lat = item.lat.toDouble(),
                lng = item.lng.toDouble(),
                isFavorite = item.isFavorite,
                onFavoriteClicked = { onFavoriteClicked(it, item.id) },
                onItemClicked = {}
            )
        }
    }
}