package com.amandaluz.core.usecase.popularusecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amandaluz.core.module.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface GetPopularUseCase {
    operator fun invoke(params: GetPopularParams): Flow<PagingData<PhotoDomain>>
    data class GetPopularParams(val pagingConfig: PagingConfig)
}
