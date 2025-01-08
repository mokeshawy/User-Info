package com.tawuniya.userinfo.core.api_services.di

import com.tawuniya.userinfo.core.api_services.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServicesModule {

    @Singleton
    @Provides
    fun provideCompetitionsServices(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)
}