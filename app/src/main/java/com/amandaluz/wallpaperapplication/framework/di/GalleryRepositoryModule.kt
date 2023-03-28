package com.amandaluz.wallpaperapplication.framework.di

import com.amandaluz.core.data.dbrepository.GalleryLocalDataSource
import com.amandaluz.core.data.dbrepository.GalleryRepository
import com.amandaluz.wallpaperapplication.framework.db.repository.GalleryRepositoryImpl
import com.amandaluz.wallpaperapplication.framework.local.GalleryLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GalleryRepositoryModule {

    @Binds
    fun bindGalleryRepository(galleryRepositoryImpl : GalleryRepositoryImpl): GalleryRepository

    @Binds
    fun bindLocalDataSource(localDataSourceImpl : GalleryLocalDataSourceImpl): GalleryLocalDataSource
}