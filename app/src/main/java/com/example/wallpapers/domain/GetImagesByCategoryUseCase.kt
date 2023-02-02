package com.example.wallpapers.domain


class GetImagesByCategoryUseCase(private val repository: WallpapersRepository) {
    suspend operator fun invoke(category: Category): List<Wallpaper> {
        return repository.getWallpapersByCategory(category)
    }
}