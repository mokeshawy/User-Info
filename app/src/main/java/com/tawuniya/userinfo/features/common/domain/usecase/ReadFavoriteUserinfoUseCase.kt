package com.tawuniya.userinfo.features.common.domain.usecase

import com.tawuniya.userinfo.features.common.domain.mapper.toUserEntitiesUiModel
import com.tawuniya.userinfo.features.common.domain.repository.ReadFavoriteUserinfoRepository
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject


class ReadFavoriteUserinfoUseCase @Inject constructor(private val readFavoriteUserinfoRepository: ReadFavoriteUserinfoRepository) {

    operator fun invoke() = channelFlow {
        readFavoriteUserinfoRepository.getFavoriteUser().collect { list ->
            val userEntitiesUiModelList = list.map { it.toUserEntitiesUiModel() }
            send(userEntitiesUiModelList)
        }
    }
}
