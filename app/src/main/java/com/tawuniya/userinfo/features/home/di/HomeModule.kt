package com.tawuniya.userinfo.features.home.di

import com.tawuniya.userinfo.core.api_services.ApiServices
import com.tawuniya.userinfo.features.home.data.repository.UserInfoRepositoryImpl
import com.tawuniya.userinfo.features.home.domain.repository.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideUserinfoRepositoryImpl(apiServices: ApiServices): UserInfoRepository =
        UserInfoRepositoryImpl(apiServices = apiServices)
}