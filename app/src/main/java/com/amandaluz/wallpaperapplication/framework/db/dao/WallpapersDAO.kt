package com.amandaluz.wallpaperapplication.framework.db.dao

import androidx.room.Dao
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

    @Query("DELETE * FROM ${DBConstants.PHOTO_TABLE_NAME} WHERE id = :id")
    suspend fun deletePhotoById(id: Int)

}