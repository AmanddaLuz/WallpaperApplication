package com.amandaluz.wallpaperapplication.framework.local

import com.amandaluz.core.data.dbrepository.GalleryLocalDataSource
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.wallpaperapplication.framework.db.dao.WallpapersDAO
import com.amandaluz.wallpaperapplication.framework.db.entity.PhotoEntity
import com.amandaluz.wallpaperapplication.framework.db.entity.toPhotoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GalleryLocalDataSourceImpl @Inject constructor(
    private val dao: WallpapersDAO
): GalleryLocalDataSource {
    override suspend fun insert(domain : PhotoDomain) = dao.insert(domain.toEntity())

    override suspend fun get() : Flow<List<PhotoDomain>> = dao.getAllPhotos().map {
        it.toPhotoDomain()
    }

    override suspend fun delete(domain : PhotoDomain) = dao.deletePhotoById(domain.toEntity())

    private fun PhotoDomain.toEntity() = PhotoEntity(
        id = this.id?: 0,
        photo = this.srcDomain?.original?: "",
        smallPhoto = this.srcDomain?.small?: "",
        photographer = this.photographer?: "",
        avgColor = this.avgColor?: "",
    )
}