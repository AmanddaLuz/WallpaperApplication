package com.amandaluz.wallpaperapplication.framework.network.api

import com.amandaluz.wallpaperapplication.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpapersAPI {

    @GET("v1/curated")
    suspend fun getPopularWallpapers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): DataWrapperResponse
}