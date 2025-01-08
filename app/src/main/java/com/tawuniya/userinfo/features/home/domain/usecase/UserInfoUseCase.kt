package com.tawuniya.userinfo.features.home.domain.usecase

import com.tawuniya.userinfo.core.state.State
import com.tawuniya.userinfo.features.home.data.model.response.UserInfoResponseDto
import com.tawuniya.userinfo.features.home.domain.mapper.toUserInfoUiModel
import com.tawuniya.userinfo.features.home.domain.repository.UserInfoRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {

    private var userInfoResponseDto: List<UserInfoResponseDto>? = null

    operator fun invoke() = channelFlow {
        val response = async { userInfoRepository.getUserInfo() }
        response.await().collect {
            if (it is State.Success) {
                userInfoResponseDto = it.data
            }
            send(it)
        }
    }

    fun getUserInfoUiModelList() = userInfoResponseDto?.map { it.toUserInfoUiModel() }
}