package com.amandaluz.wallpaperapplication.framework.workmanager

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.amandaluz.wallpaperapplication.framework.db.dao.WallpapersDAO
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

@HiltWorker
class WallpaperWork @AssistedInject constructor(
    @Assisted context : Context,
    @Assisted workerParameters : WorkerParameters,
    private val wallpaperManager : WallpaperManager
): CoroutineWorker(context, workerParameters){
    @Inject
    lateinit var dao: WallpapersDAO

    override suspend fun doWork() : Result = withContext(Dispatchers.IO){
        try {
            val photo = dao.getRandomWallpaper()
            converterUrlToBitmap(photo.photo)
            Result.success()
        }catch (exception: Exception){
            exception.printStackTrace()
            Result.failure()
        }
    }

    private fun converterUrlToBitmap(url: String){
        try {
            val bitmap = BitmapFactory.decodeStream(URL(url).openStream())
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
        }catch (exception: Exception){
            exception.printStackTrace()
        }
    }
}