package com.example.wallpapers.domain


class GetImagesByCategoryUseCase(private val repository: WallpapersRepository) {
    operator fun invoke(categoryName: String): List<Wallpaper> {
        return repository.getWallpapersByCategory(categoryName)
    }
}