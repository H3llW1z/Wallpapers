package com.example.wallpapers.domain

interface WallpapersRepository {
    suspend fun getWallpapersByCategory(category: Category): Result<List<Wallpaper>>

}