package com.amandaluz.wallpaperapplication.ui.fragments.adapter.photoadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.wallpaperapplication.R
import com.amandaluz.wallpaperapplication.databinding.ItemPhotoBinding
import com.bumptech.glide.Glide

class PhotoViewHolder(
    itemPhotoBinding: ItemPhotoBinding ,
    private val photoCallback: (photo: PhotoDomain) -> Unit ,
) :
    RecyclerView.ViewHolder(itemPhotoBinding.root) {
    private val image = itemPhotoBinding.image
    private val name = itemPhotoBinding.name

    fun bind(photo: PhotoDomain) {
        Glide.with(itemView.context)
            .load(photo.srcDomain.original)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(image)

        name.text = photo.photographer
        itemView.setOnClickListener {
            photoCallback.invoke(photo)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            photoCallback: (photo: PhotoDomain) -> Unit,
        ): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(itemBinding, photoCallback)
        }
    }
}
