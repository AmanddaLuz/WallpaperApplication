package com.amandaluz.wallpaperapplication.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amandaluz.core.data.DBConstants

@Entity(tableName = DBConstants.PHOTO_TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey
    val id : Int ,
    val photo : String
)