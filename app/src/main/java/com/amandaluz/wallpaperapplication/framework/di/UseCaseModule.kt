package com.amandaluz.wallpaperapplication.framework.di

import com.amandaluz.core.usecase.deletegalleryusecase.DeleteGalleryUseCase
import com.amandaluz.core.usecase.deletegalleryusecase.DeleteGalleryUseCaseImpl
import com.amandaluz.core.usecase.getgalleryusecase.GetGalleryUseCase
import com.amandaluz.core.usecase.getgalleryusecase.GetGalleryUseCaseImpl
import com.amandaluz.core.usecase.insertgalleryusecase.InsertGalleryUseCase
import com.amandaluz.core.usecase.insertgalleryusecase.InsertGalleryUseCaseImpl
import com.amandaluz.core.usecase.popularusecase.GetPopularUseCase
import com.amandaluz.core.usecase.popularusecase.GetPopularUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindPopularUseCase(getPopularUseCaseImpl: GetPopularUseCaseImpl): GetPopularUseCase

    @Binds
    fun bindGetGalleryUseCase(getGalleryUseCaseImpl : GetGalleryUseCaseImpl): GetGalleryUseCase
    @Binds
    fun bindInsertGalleryUseCase(insertGalleryUseCaseImpl : InsertGalleryUseCaseImpl): InsertGalleryUseCase

    @Binds
    fun bindDeleteGalleryUseCase(deleteGalleryUseCaseImpl : DeleteGalleryUseCaseImpl): DeleteGalleryUseCase
}