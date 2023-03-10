package com.amandaluz.core.data

import androidx.paging.PagingSource
import com.amandaluz.core.module.PhotoDomain

interface PopularRepository {

    fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain>
}