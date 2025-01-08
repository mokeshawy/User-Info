package com.tawuniya.userinfo.features.home.data.repository

import com.tawuniya.userinfo.core.api_services.ApiServices
import com.tawuniya.userinfo.core.bases.base_repository.BaseRepository
import com.tawuniya.userinfo.core.state.State
import com.tawuniya.userinfo.features.home.data.model.response.UserInfoResponseDto
import com.tawuniya.userinfo.features.home.domain.repository.UserInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository<Any, List<UserInfoResponseDto>>(), UserInfoRepository {


    override suspend fun getUserInfo() = flow {
        emit(getOperationState(Any()))
    }.flowOn(Dispatchers.IO)


    override suspend fun performApiCall(requestDto: Any): State<List<UserInfoResponseDto>> {
        val response = apiServices.getUserInfo()
        return response.handleUserinfoResponseState()
    }


    private fun Response<List<UserInfoResponseDto>>.handleUserinfoResponseState(): State<List<UserInfoResponseDto>> {
        return when {
            isSuccessful -> State.Success(this.body())
            else -> getNotSuccessfulResponseState(response = this)
        }
    }
}