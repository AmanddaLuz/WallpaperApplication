package com.amandaluz.wallpaperapplication.framework.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.amandaluz.core.data.DBConstants
import com.amandaluz.wallpaperapplication.framework.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpapersDAO {

    @Insert()
    suspend fun insert(photoEntity : PhotoEntity)

    @Query("SELECT * FROM ${DBConstants.PHOTO_TABLE_NAME}")
    fun getAllPhotos(): Flow<List<PhotoEntity>>

    @Delete
    suspend fun deletePhotoById(entity : PhotoEntity)

    @Query("SELECT * FROM ${DBConstants.PHOTO_TABLE_NAME} ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWallpaper(): PhotoEntity

}