package com.amandaluz.wallpaperapplication.ui.fragments.adapter.galleryadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.wallpaperapplication.ui.fragments.adapter.photoadapter.PhotoViewHolder

class GalleryAdapter(
    private val clickCallback : ((photo : PhotoDomain) -> Unit) ,
    private val longClickCallback : ((photo : PhotoDomain) -> Unit)
) : ListAdapter<PhotoDomain, PhotoViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : PhotoViewHolder {
        return PhotoViewHolder.create(parent, clickCallback, longClickCallback)
    }

    override fun onBindViewHolder(holder : PhotoViewHolder , position : Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<PhotoDomain>() {
            override fun areItemsTheSame(oldItem : PhotoDomain , newItem : PhotoDomain) : Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(
                oldItem : PhotoDomain ,
                newItem : PhotoDomain
            ) : Boolean {
                return oldItem == newItem
            }
        }
    }
}