package com.amandaluz.wallpaperapplication.ui.fragments.adapter.photoadapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.amandaluz.core.module.PhotoDomain

class PhotoAdapter(
    private val clickCallback: ((photo: PhotoDomain) -> Unit) ,
    private val longClickCallback: ((photo: PhotoDomain) -> Unit)
) : PagingDataAdapter<PhotoDomain, PhotoViewHolder>(differCallback) {
    override fun onBindViewHolder(holder: PhotoViewHolder , position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder.create(parent , clickCallback, longClickCallback)

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<PhotoDomain>() {
            override fun areItemsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
                return oldItem == newItem
            }
        }
    }
}