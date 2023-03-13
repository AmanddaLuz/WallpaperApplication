package com.amandaluz.wallpaperapplication.ui.fragments.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase : GetPopularUseCase
) : ViewModel() {

    fun popularWallpapers() : Flow<PagingData<PhotoDomain>> {
        return popularUseCase(GetPopularUseCase.GetPopularParams(pagingConfig())).cachedIn(
            viewModelScope
        )
    }

    fun pagingConfig() = PagingConfig(pageSize = PAGE_SIZE)


    companion object {
        const val PAGE_SIZE = 40
    }
}