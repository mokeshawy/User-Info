package com.tawuniya.userinfo.features.home.domain.model.ui


data class UserInfoUiModel(
    val address: AddressUiModel,
    val company: CompanyUiModel,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    var isFavorite: Boolean = false
)
