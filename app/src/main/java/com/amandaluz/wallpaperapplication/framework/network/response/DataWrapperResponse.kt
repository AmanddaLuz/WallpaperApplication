package com.amandaluz.wallpaperapplication.framework.network.response

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("photos")
    val photos: List<Photo>)