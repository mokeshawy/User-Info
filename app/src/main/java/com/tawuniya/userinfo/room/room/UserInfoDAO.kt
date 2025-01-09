package com.tawuniya.userinfo.room.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawuniya.userinfo.features.common.data.model.UserInfoEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteUser(userInfoEntities: UserInfoEntities)

    @Query("DELETE FROM userinfoentities WHERE id = :id")
    suspend fun unFavoriteUser(id: Int)

    @Query("SELECT * FROM userinfoentities WHERE isFavorite = 1 ")
    suspend fun getFavoriteUser(): List<UserInfoEntities>

    @Query("SELECT * FROM userinfoentities")
    fun getAllFavoriteUser(): Flow<List<UserInfoEntities>>

}