package com.amandaluz.core.module

data class PhotoDomain(
    var description: String,
    var avgColor: String,
    var height: Int,
    var id: Int,
    var liked: Boolean,
    var photographer: String,
    var photographerId: Int,
    var photographerUrl: String,
    var srcDomain: SrcDomain,
    var url: String,
    var width: Int
)