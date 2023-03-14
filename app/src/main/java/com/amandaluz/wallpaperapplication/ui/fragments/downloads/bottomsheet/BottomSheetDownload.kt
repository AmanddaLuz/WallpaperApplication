package com.amandaluz.wallpaperapplication.ui.fragments.downloads.bottomsheet

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.amandaluz.wallpaperapplication.R
import com.amandaluz.wallpaperapplication.databinding.BottomSheetDownloadBinding
import com.amandaluz.wallpaperapplication.framework.downloader.androiddownloader.AndroidDownloader
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.imageview.ShapeableImageView
import java.io.IOException

class BottomSheetDownload(
    private val url : String ,
    private val description : String
) : BottomSheetDialogFragment() {

    private lateinit var binding : BottomSheetDownloadBinding
    private lateinit var downloader : AndroidDownloader

    override fun onCreateView(
        inflater : LayoutInflater ,
        container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View {
        binding = BottomSheetDownloadBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        initButtons()
    }

    private fun initButtons() {
        binding.run {
            downLoadFromNet.setOnClickListener { downloadFromNet(url , description) }
            setAsBackground.setOnClickListener { setAsBackground(HOME_SCREEN) }
            setAsLockscreen.setOnClickListener { setAsBackground(LOCK_SCREEN) }
        }
    }

    private fun downloadFromNet(url : String , description : String) {
        downloader = AndroidDownloader(requireContext())
        downloader.downloadFile(url , description)
    }

    private fun setAsBackground(lockOrBackground : Int) {
        try {
            val wallpaperManager = WallpaperManager.getInstance(context)
            val image = activity?.findViewById<ShapeableImageView>(R.id.download_image)
            if (image?.drawable == null) {
                Toast.makeText(context , "Wait to download" , Toast.LENGTH_LONG).show()
            } else {
                val bitmap = (image.drawable as BitmapDrawable).bitmap
                wallpaperManager.setBitmap(bitmap , null , true , lockOrBackground)
                Toast.makeText(context , "Done" , Toast.LENGTH_LONG).show()
            }
        } catch (e : IOException) {
            Toast.makeText(context , e.message , Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val HOME_SCREEN = 1
        const val LOCK_SCREEN = 2
    }
}