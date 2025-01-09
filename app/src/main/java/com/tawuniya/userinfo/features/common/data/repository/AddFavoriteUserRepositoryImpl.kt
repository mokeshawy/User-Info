package com.tawuniya.userinfo.features.common.data.repository

import com.tawuniya.userinfo.features.common.data.model.UserInfoEntities
import com.tawuniya.userinfo.features.common.domain.repository.AddFavoriteUserRepository
import com.tawuniya.userinfo.features.home.domain.model.ui.UserInfoUiModel
import com.tawuniya.userinfo.room.room.UserInfoDAO
import javax.inject.Inject

class AddFavoriteUserRepositoryImpl @Inject constructor(private val userInfoDAO: UserInfoDAO) :
    AddFavoriteUserRepository {


    override suspend fun addFavoriteUserInfo(
        userInfoUiModel: UserInfoUiModel,
        isFavorite: Boolean
    ) {
        val userInfoEntities = userInfoUiModel.toUserInfoEntities(isFavorite = isFavorite)
        userInfoDAO.addFavoriteUser(userInfoEntities = userInfoEntities)
    }

    private fun UserInfoUiModel.toUserInfoEntities(isFavorite: Boolean) = UserInfoEntities(
        email = email,
        id = id,
        name = name,
        phone = phone,
        username = username,
        website = website,
        lat = address.geofence.lat,
        lng = address.geofence.lng,
        isFavorite = isFavorite
    )
}