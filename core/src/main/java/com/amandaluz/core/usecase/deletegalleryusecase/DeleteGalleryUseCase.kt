package com.amandaluz.core.usecase.deletegalleryusecase

import com.amandaluz.core.data.dbrepository.GalleryRepository
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.usecase.base.CoroutinesDispatchers
import com.amandaluz.core.usecase.base.ResultStatus
import com.amandaluz.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DeleteGalleryUseCase {
    operator fun invoke(params : Params) : Flow<ResultStatus<Unit>>
    data class Params(val photoDomain : PhotoDomain)
}

class DeleteGalleryUseCaseImpl @Inject constructor(
    private val galleryRepository : GalleryRepository ,
    private val dispatchers : CoroutinesDispatchers
) : UseCase<DeleteGalleryUseCase.Params , Unit>() , DeleteGalleryUseCase {
    override suspend fun doWork(params : DeleteGalleryUseCase.Params) : ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            galleryRepository.delete(params.photoDomain)
            ResultStatus.Success (Unit) }
    }
}