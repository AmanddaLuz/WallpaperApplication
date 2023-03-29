package com.amandaluz.core.usecase.insertgalleryusecase

import com.amandaluz.core.data.dbrepository.GalleryRepository
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.usecase.base.CoroutinesDispatchers
import com.amandaluz.core.usecase.base.ResultStatus
import com.amandaluz.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface InsertGalleryUseCase { operator fun invoke(params: Params): Flow<ResultStatus<Unit>>


data class Params(val photoDomain: PhotoDomain) }

class InsertGalleryUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository ,
    private val dispatchers: CoroutinesDispatchers
    ) : UseCase<InsertGalleryUseCase.Params , Unit>(), InsertGalleryUseCase {

    override suspend fun doWork(params: InsertGalleryUseCase.Params): ResultStatus<Unit> {
    return withContext(dispatchers.io()) {
        repository.insert(params.photoDomain)
        ResultStatus.Success(Unit)
    }
}
}