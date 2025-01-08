package com.tawuniya.userinfo.features.home.domain.model.ui


data class AddressUiModel(
    val city: String,
    val geofence: GeofenceUiModel,
    val street: String,
    val suite: String,
    val zipcode: String
)