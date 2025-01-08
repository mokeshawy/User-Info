package com.tawuniya.userinfo.features.home.domain.events

import com.tawuniya.userinfo.core.bases.base_viewmodel.ViewIntent

sealed class HomeIntent : ViewIntent{
    data object GetUserInfo : HomeIntent()
}