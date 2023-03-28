package com.amandaluz.core.module

data class PhotoDomain(
    var description: String? = "",
    var avgColor: String? = "",
    var id: Int? = 0,
    var photographer: String? = "",
    var photographerId: Int? = 0,
    var photographerUrl: String? = "",
    var srcDomain: SrcDomain?,
    var url: String? = "",
)