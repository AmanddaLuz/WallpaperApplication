package com.amandaluz.wallpaperapplication.framework.downloader.androiddownloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(context : Context) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)
    override fun downloadFile(url : String , description : String) : Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("image $description")
            .addRequestHeader("Authorization" , "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS , "image.jpg")
        return downloadManager.enqueue(request)
    }
}