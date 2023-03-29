package com.amandaluz.wallpaperapplication.ui.fragments.gallery.viewmodel

import androidx.lifecycle.ViewModel
import com.amandaluz.core.usecase.deletegalleryusecase.DeleteGalleryUseCase
import com.amandaluz.core.usecase.getgalleryusecase.GetGalleryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getGalleryUseCase : GetGalleryUseCase,
    private val deleteFavoriteUseCase : DeleteGalleryUseCase

): ViewModel() {
}