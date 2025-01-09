package com.tawuniya.userinfo.room.di

import android.content.Context
import androidx.room.Room
import com.tawuniya.userinfo.room.room.UserInfoDAO
import com.tawuniya.userinfo.room.room.USER_INFO_DATABASE
import com.tawuniya.userinfo.room.room.UserInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideUserDao(userInfoDatabase: UserInfoDatabase): UserInfoDAO = userInfoDatabase.userInfoDao()


    @Provides
    @Singleton
    fun provideUserInfoDatabase(@ApplicationContext context: Context): UserInfoDatabase =
        Room.databaseBuilder(
            context = context,
            klass = UserInfoDatabase::class.java,
            name = USER_INFO_DATABASE
        ).fallbackToDestructiveMigration().build()
}