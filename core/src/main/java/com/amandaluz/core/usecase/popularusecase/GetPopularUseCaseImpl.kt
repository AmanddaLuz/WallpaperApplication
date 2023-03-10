package com.amandaluz.core.usecase.popularusecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.amandaluz.core.data.PopularRepository
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.usecase.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularUseCaseImpl @Inject constructor(
    private val repository: PopularRepository
): PagingUseCase<GetPopularUseCase.GetPopularParams , PhotoDomain>(), GetPopularUseCase {
    override fun createFlowObservable(params: GetPopularUseCase.GetPopularParams): Flow<PagingData<PhotoDomain>> {
        return Pager(config = params.pagingConfig) {
            repository.fetchPopular(pages = params.pagingConfig.pageSize)
        }.flow
    }
}