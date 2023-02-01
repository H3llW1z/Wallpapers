package com.example.wallpapers.domain

interface WallpapersRepository {

    fun getWallpapersByCategory(categoryName: String): List<Wallpaper>

    fun getCategories(): List<Category>
}