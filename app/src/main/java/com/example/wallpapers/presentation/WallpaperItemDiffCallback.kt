package com.example.wallpapers.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.wallpapers.domain.Wallpaper

class WallpaperItemDiffCallback: DiffUtil.ItemCallback<Wallpaper>() {
    override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean =
        oldItem == newItem
}