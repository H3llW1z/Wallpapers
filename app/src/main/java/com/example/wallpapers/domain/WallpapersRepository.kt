package com.example.wallpapers.domain

interface WallpapersRepository {
    suspend fun getWallpapersByCategory(category: Category): List<Wallpaper>

}