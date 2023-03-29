package com.amandaluz.wallpaperapplication.ui.fragments.adapter.photoadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.wallpaperapplication.databinding.ItemPhotoBinding
import com.amandaluz.wallpaperapplication.ui.extensions.loadBlurredImageWithPlaceholder

class PhotoViewHolder(
    itemPhotoBinding : ItemPhotoBinding ,
    private val clickCallback : (photo : PhotoDomain) -> Unit ,
    private val longClickCallback : (photo : PhotoDomain) -> Unit ,
) :
    RecyclerView.ViewHolder(itemPhotoBinding.root) {
    private val image = itemPhotoBinding.image
    private val name = itemPhotoBinding.name

    fun bind(photo: PhotoDomain) {

        image.loadBlurredImageWithPlaceholder(
            imageUrl = photo.srcDomain?.original,
            placeholderColor = photo.avgColor
        )
        name.text = photo.photographer
        itemView.setOnClickListener {
            clickCallback.invoke(photo)
        }

        itemView.setOnLongClickListener {
            longClickCallback.invoke(photo)
            return@setOnLongClickListener true
        }
    }

    companion object {
        fun create(
            parent : ViewGroup ,
            clickCallback : (photo : PhotoDomain) -> Unit ,
            longClickCallback : (photo : PhotoDomain) -> Unit ,
        ): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(itemBinding, clickCallback, longClickCallback)
        }
    }
}
