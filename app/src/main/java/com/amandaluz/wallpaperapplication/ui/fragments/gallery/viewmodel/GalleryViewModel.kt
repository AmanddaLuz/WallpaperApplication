package com.amandaluz.wallpaperapplication.ui.fragments.gallery.viewmodel

import androidx.lifecycle.*
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.usecase.base.CoroutinesDispatchers
import com.amandaluz.core.usecase.deletegalleryusecase.DeleteGalleryUseCase
import com.amandaluz.core.usecase.getgalleryusecase.GetGalleryUseCase
import com.amandaluz.wallpaperapplication.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getGalleryUseCase : GetGalleryUseCase ,
    private val deleteFavoriteUseCase : DeleteGalleryUseCase ,
    private val coroutineDispatcher : CoroutinesDispatchers

) : ViewModel() {

    private val action = MutableLiveData<Action>()

    init {
        getGallery()
    }

    val state : LiveData<UiState> = action.switchMap { action ->
        liveData(coroutineDispatcher.main()) {
            when (action) {
                is Action.GetGallery -> getPhotos()
                is Action.DeleteFavorite -> deletePhotos(action)
            }
        }
    }

    private suspend fun LiveDataScope<UiState>.getPhotos() {
        getGalleryUseCase.invoke().catch {
            emit(UiState.Error)
        }
            .collect {
                val uiState = if (it.isEmpty()) UiState.EmptyGallery else UiState.ShowGallery(it)
                emit(uiState)
            }
    }

    private suspend fun LiveDataScope<UiState>.deletePhotos(action : Action.DeleteFavorite) {
        deleteFavoriteUseCase(
            DeleteGalleryUseCase.Params(action.photoDomain)
        ).watchStatus(
            loading = {} ,
            success = { getGallery() } ,
            error = {emit(UiState.Error)} // Dica: poderia mandar uma mensagem dizendo o que houve de errado
        )
    }

    private fun getGallery() {
        action.value = Action.GetGallery
    }

    fun delete(photoDomain : PhotoDomain) {
        action.value = Action.DeleteFavorite(photoDomain)
    }

    sealed class Action {
        object GetGallery : Action()
        data class DeleteFavorite(val photoDomain : PhotoDomain) : Action()
    }

    sealed class UiState {
        object EmptyGallery : UiState()
        object Error : UiState()
        data class ShowGallery(val photo : List<PhotoDomain>) : UiState()
    }
}