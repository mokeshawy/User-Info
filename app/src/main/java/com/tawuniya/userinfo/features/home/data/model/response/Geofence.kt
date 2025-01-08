package com.tawuniya.userinfo.features.home.data.model.response


import com.google.gson.annotations.SerializedName

data class Geofence(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)