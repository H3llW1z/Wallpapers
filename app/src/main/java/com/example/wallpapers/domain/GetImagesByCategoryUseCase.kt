package com.example.wallpapers.domain

import javax.inject.Inject


class GetImagesByCategoryUseCase @Inject constructor (private val repository: WallpapersRepository) {
    suspend operator fun invoke(category: Category): Result<List<Wallpaper>> {
        return repository.getWallpapersByCategory(category)
    }
}