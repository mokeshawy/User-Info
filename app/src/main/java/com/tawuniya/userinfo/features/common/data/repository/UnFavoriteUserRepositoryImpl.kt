package com.tawuniya.userinfo.features.common.data.repository

import com.tawuniya.userinfo.features.common.domain.repository.UnFavoriteUserRepository
import com.tawuniya.userinfo.room.room.UserInfoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UnFavoriteUserRepositoryImpl @Inject constructor(private val userInfoDAO: UserInfoDAO) :
    UnFavoriteUserRepository {

    override suspend fun unFavoriteUserInfo(id: Int) = withContext(Dispatchers.IO) {
        userInfoDAO.unFavoriteUser(id = id)
    }
}