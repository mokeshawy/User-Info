package com.tawuniya.userinfo.features.common.di

import com.tawuniya.userinfo.features.common.data.repository.AddFavoriteUserRepositoryImpl
import com.tawuniya.userinfo.features.common.data.repository.ReadFavoriteUserinfoRepositoryImpl
import com.tawuniya.userinfo.features.common.data.repository.UnFavoriteUserRepositoryImpl
import com.tawuniya.userinfo.features.common.domain.repository.AddFavoriteUserRepository
import com.tawuniya.userinfo.features.common.domain.repository.ReadFavoriteUserinfoRepository
import com.tawuniya.userinfo.features.common.domain.repository.UnFavoriteUserRepository
import com.tawuniya.userinfo.room.room.UserInfoDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object CommonModule {

    @Provides
    fun provideReadFavoriteUserRepositoryImpl(userInfoDAO: UserInfoDAO): ReadFavoriteUserinfoRepository =
        ReadFavoriteUserinfoRepositoryImpl(userInfoDAO = userInfoDAO)

    @Provides
    fun provideAddFavoriteUserRepositoryImpl(userInfoDAO: UserInfoDAO): AddFavoriteUserRepository =
        AddFavoriteUserRepositoryImpl(userInfoDAO = userInfoDAO)

    @Provides
    fun provideUnFavoriteUserRepositoryImpl(userInfoDAO: UserInfoDAO): UnFavoriteUserRepository =
        UnFavoriteUserRepositoryImpl(userInfoDAO = userInfoDAO)
}