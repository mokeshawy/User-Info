package com.tawuniya.userinfo.features.common.domain.model

data class UserInfoEntitiesUiModel(
    val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val lat: String,
    val lng: String
)
