package com.amandaluz.wallpaperapplication.framework.downloader.androiddownloader

interface Downloader {
    fun downloadFile(url: String, description: String): Long
}