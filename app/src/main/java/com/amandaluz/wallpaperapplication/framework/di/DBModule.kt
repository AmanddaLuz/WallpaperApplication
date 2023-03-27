package com.amandaluz.wallpaperapplication.framework.di

import android.content.Context
import androidx.room.Room
import com.amandaluz.core.data.DBConstants
import com.amandaluz.wallpaperapplication.framework.db.WallpappersDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    fun providesAppDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, WallpappersDataBase::class.java, DBConstants.APP_DATABASE_NAME
    ).build()

    @Provides
    fun providesWallpappersDao(db: WallpappersDataBase) = db.wallpappersDao()
}