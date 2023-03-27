package com.amandaluz.wallpaperapplication.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amandaluz.wallpaperapplication.framework.db.dao.WallpapersDAO
import com.amandaluz.wallpaperapplication.framework.db.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class WallpappersDataBase: RoomDatabase() {

    abstract fun wallpappersDao(): WallpapersDAO
}