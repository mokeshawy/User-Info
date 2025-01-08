package com.tawuniya.userinfo.features.home.domain.mapper

import com.tawuniya.userinfo.features.home.data.model.response.Address
import com.tawuniya.userinfo.features.home.data.model.response.Company
import com.tawuniya.userinfo.features.home.data.model.response.Geofence
import com.tawuniya.userinfo.features.home.data.model.response.UserInfoResponseDto
import com.tawuniya.userinfo.features.home.domain.model.ui.AddressUiModel
import com.tawuniya.userinfo.features.home.domain.model.ui.CompanyUiModel
import com.tawuniya.userinfo.features.home.domain.model.ui.GeofenceUiModel
import com.tawuniya.userinfo.features.home.domain.model.ui.UserInfoUiModel


fun UserInfoResponseDto.toUserInfoUiModel() = UserInfoUiModel(
    address = address.toAddressUiModel(),
    company = company.toCompanyUiModel(),
    email = email,
    id = id,
    name = name,
    phone = phone,
    username = username,
    website = website,
)

private fun Address.toAddressUiModel() = AddressUiModel(
    city = city,
    geofence = geofence.toGeofenceUiModel(),
    street = street,
    suite = suite,
    zipcode = zipcode,
)

private fun Geofence.toGeofenceUiModel() = GeofenceUiModel(
    lat = lat,
    lng = lng
)

private fun Company.toCompanyUiModel() = CompanyUiModel(
    bs = bs,
    catchPhrase = catchPhrase,
    name = name
)