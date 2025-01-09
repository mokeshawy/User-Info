package com.tawuniya.userinfo.features.common.data.repository

import com.tawuniya.userinfo.features.common.domain.repository.ReadFavoriteUserinfoRepository
import com.tawuniya.userinfo.room.room.UserInfoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReadFavoriteUserinfoRepositoryImpl @Inject constructor(private val userInfoDAO: UserInfoDAO) :
    ReadFavoriteUserinfoRepository {

    override suspend fun getFavoriteUser() = withContext(Dispatchers.IO) {
        userInfoDAO.getFavoriteUser()
    }
}