package com.amandaluz.core.usecase.getgalleryusecase

import com.amandaluz.core.data.dbrepository.GalleryRepository
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.usecase.base.CoroutinesDispatchers
import com.amandaluz.core.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetGalleryUseCase {
    suspend operator fun invoke(params : Unit) : Flow<List<PhotoDomain>>
}

class GetGalleryUseCaseImpl @Inject constructor(
    private val repository : GalleryRepository ,
    private val dispatcher : CoroutinesDispatchers
) : FlowUseCase<Unit , List<PhotoDomain>>() , GetGalleryUseCase {

    override suspend fun createFlowObservable(params : Unit) : Flow<List<PhotoDomain>> {
        return withContext(dispatcher.io()){
            repository.get()
        }
    }
}