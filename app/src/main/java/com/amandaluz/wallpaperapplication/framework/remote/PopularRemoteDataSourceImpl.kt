package com.amandaluz.wallpaperapplication.framework.remote

import com.amandaluz.core.data.RemoteDataSource
import com.amandaluz.wallpaperapplication.framework.network.api.WallpapersAPI
import com.amandaluz.wallpaperapplication.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class PopularRemoteDataSourceImpl @Inject constructor(
    private val api : WallpapersAPI
    ): RemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchPopular(page : Int , perPage : Int) : DataWrapperResponse =
        api.getPopularWallpapers(page, perPage)
}