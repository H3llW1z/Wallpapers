package com.example.wallpapers.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.wallpapers.R
import com.example.wallpapers.databinding.WallpaperItemBinding
import com.example.wallpapers.domain.Wallpaper
import com.example.wallpapers.presentation.WallpaperItemDiffCallback
import com.example.wallpapers.presentation.WallpaperViewHolder
import com.squareup.picasso.Picasso

class WallpapersAdapter : ListAdapter<Wallpaper, WallpaperViewHolder>(WallpaperItemDiffCallback()) {

    var onItemClickListener: ((Wallpaper) -> Unit)? = null
    private val picasso = Picasso.get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val binding =
            WallpaperItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WallpaperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val wallpaperItem = currentList[position]
        onItemClickListener?.let { listener ->
            holder.binding.root.setOnClickListener { listener(wallpaperItem) }
        }
        picasso.load(wallpaperItem.previewUrl)
            .placeholder(R.drawable.wallpaper_placeholder)
            .error(R.drawable.wallpaper_error_placeholder)
            .into(holder.binding.imageViewWallpaper)
    }
}