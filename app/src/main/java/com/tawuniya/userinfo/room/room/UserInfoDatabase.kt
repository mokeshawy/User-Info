package com.tawuniya.userinfo.room.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tawuniya.userinfo.features.common.data.model.UserInfoEntities

const val USER_INFO_DATABASE = "USER_INFO_DATABASE"

@Database(
    entities = [UserInfoEntities::class],
    version = 1,
    exportSchema = false
)
abstract class UserInfoDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDAO
}