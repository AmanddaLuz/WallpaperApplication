package com.amandaluz.wallpaperapplication.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amandaluz.core.data.DBConstants
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.core.module.SrcDomain

@Entity(tableName = DBConstants.PHOTO_TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey
    val id : Int ,
    val photo : String,
    val photographer: String,
    val avgColor: String
)

fun List<PhotoEntity>.toPhotoDomain() = map{
    PhotoDomain(
        id = it.id,
        photographer = it.photographer,
        avgColor = it.avgColor,
        srcDomain = SrcDomain(
            original = it.photo
        )
    )
}