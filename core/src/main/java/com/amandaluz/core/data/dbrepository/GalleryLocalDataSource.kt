package com.amandaluz.core.data.dbrepository

import com.amandaluz.core.module.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface GalleryLocalDataSource {
    suspend fun insert(domain : PhotoDomain)

    suspend fun get(): Flow<List<PhotoDomain>>

    suspend fun delete(domain : PhotoDomain)
}