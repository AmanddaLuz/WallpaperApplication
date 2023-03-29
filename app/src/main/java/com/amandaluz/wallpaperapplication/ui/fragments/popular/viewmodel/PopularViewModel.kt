package com.amandaluz.wallpaperapplication.ui.fragments.popular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.usecase.insertgalleryusecase.InsertGalleryUseCase
import com.amandaluz.core.usecase.popularusecase.GetPopularUseCase
import com.amandaluz.wallpaperapplication.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase : GetPopularUseCase,
    private val insertGalleryUseCase : InsertGalleryUseCase
) : ViewModel() {

    private val _favoriteUIState = MutableLiveData<GalleryUIState>()
    val favoriteUIState: LiveData<GalleryUIState> get() = _favoriteUIState

    fun popularWallpapers() : Flow<PagingData<PhotoDomain>> {
        return popularUseCase(GetPopularUseCase.GetPopularParams(pagingConfig())).cachedIn(
            viewModelScope
        )
    }

    private fun pagingConfig() = PagingConfig(pageSize = PAGE_SIZE)

    fun insertPhoto(photoDomain : PhotoDomain) = viewModelScope.launch {
        photoDomain.run {
            insertGalleryUseCase.invoke(InsertGalleryUseCase.Params(this))
                .watchStatus(
                    loading = {_favoriteUIState.value = GalleryUIState.Loading},
                    success = {_favoriteUIState.value = GalleryUIState.GalleryPhoto(true)},
                    error = {_favoriteUIState.value = GalleryUIState.GalleryPhoto(false)}
                )
        }
    }

    sealed class GalleryUIState{
        object Loading: GalleryUIState()

        class GalleryPhoto(val saved: Boolean): GalleryUIState()
    }


    companion object {
        const val PAGE_SIZE = 40
    }
}