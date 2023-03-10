package com.amandaluz.wallpaperapplication.framework.di

import com.amandaluz.core.data.PopularRepository
import com.amandaluz.core.data.RemoteDataSource
import com.amandaluz.wallpaperapplication.framework.network.response.DataWrapperResponse
import com.amandaluz.wallpaperapplication.framework.remote.PopularRemoteDataSourceImpl
import com.amandaluz.wallpaperapplication.framework.repository.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds //sempre retorna uma interface
    fun bindPopularRepository(repository : PopularRepositoryImpl): PopularRepository

    @Binds
    fun bindRemoteDataSource(dataSourceImpl : PopularRemoteDataSourceImpl): RemoteDataSource<DataWrapperResponse>

}