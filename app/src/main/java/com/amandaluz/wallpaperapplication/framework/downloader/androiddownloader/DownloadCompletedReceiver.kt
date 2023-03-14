package com.amandaluz.wallpaperapplication.framework.downloader.androiddownloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

const val DEFAULT_VALUE = -1L

class DownloadCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context? , intent: Intent?) {
        if(intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, DEFAULT_VALUE)
            checkIdValue(id)
        }
    }
    private fun checkIdValue(id: Long) {
        if (id != DEFAULT_VALUE) {
            println("Download with ID $id finished")
        }
    }
}