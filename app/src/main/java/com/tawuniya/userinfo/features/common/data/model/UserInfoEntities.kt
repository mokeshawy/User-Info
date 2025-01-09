package com.tawuniya.userinfo.features.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfoEntities(
    @PrimaryKey
    val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val lat: String,
    val lng: String,
    val isFavorite: Boolean = false
)
