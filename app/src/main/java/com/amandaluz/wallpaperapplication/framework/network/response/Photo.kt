package com.amandaluz.wallpaperapplication.framework.network.response

import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.module.SrcDomain
import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("avg_color")
    val avgColor: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("photographer")
    val photographer: String,
    @SerializedName("photographer_id")
    val photographerId: Int,
    @SerializedName("photographer_url")
    val photographerUrl: String,
    @SerializedName("src")
    val src: Src,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)
fun Photo.toPhotoDomain(): PhotoDomain =
    PhotoDomain(
        description = this.alt,
        avgColor = this.avgColor,
        id = this.id,
        photographer = this.photographer,
        photographerId = this.photographerId,
        photographerUrl = this.photographerUrl,
        srcDomain = SrcDomain(
            landscape = this.src.landscape,
            large = this.src.large,
            large2x = this.src.large2x,
            medium = this.src.medium,
            original = this.src.original,
            portrait = this.src.portrait,
            small = this.src.small,
            tiny = this.src.tiny,
        ) ,
        url = this.url,
    )
