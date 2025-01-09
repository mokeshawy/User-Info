package com.tawuniya.userinfo.features.common.domain.mapper

import com.tawuniya.userinfo.features.common.data.model.UserInfoEntities
import com.tawuniya.userinfo.features.common.domain.model.ui.UserInfoEntitiesUiModel


fun UserInfoEntities.toUserEntitiesUiModel() = UserInfoEntitiesUiModel(
    id = id,
    email = email,
    name = name,
    phone = phone,
    username = username,
    website = website,
    lat = lat,
    lng = lng,
    isFavorite = isFavorite
)