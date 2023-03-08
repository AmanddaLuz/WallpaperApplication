package com.amandaluz.wallpaperapplication.framework.repository

import androidx.paging.PagingSource
import com.amandaluz.core.data.PopularRepository
import com.amandaluz.core.data.RemoteDataSource
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.wallpaperapplication.framework.network.response.DataWrapperResponse
import com.amandaluz.wallpaperapplication.framework.paging.PopularPagingSource
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val remoteDataSource : RemoteDataSource<DataWrapperResponse>
): PopularRepository {
    override fun fetchPopular(pages : Int) : PagingSource<Int , PhotoDomain> {
        return PopularPagingSource(remoteDataSource, pages)
    }
}