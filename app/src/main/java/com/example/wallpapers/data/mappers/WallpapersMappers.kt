package com.example.wallpapers.data.mappers

import com.example.wallpapers.data.api.model.WallpaperDto
import com.example.wallpapers.domain.Category
import com.example.wallpapers.domain.Wallpaper

fun WallpaperDto.toEntity(): Wallpaper {
    return Wallpaper(
        id = id,
        previewUrl = previewURL,
        largeImageUrl = largeImageURL
    )
}

fun mapCategoryToCategoryName(category: Category): String {
    return when (category) {
        Category.NATURE -> "nature"
        Category.FOOD -> "food"
        Category.SCIENCE -> "science"
        Category.PEOPLE -> "people"
        Category.FEELINGS -> "feelings"
        Category.RELIGION -> "religion"
        Category.INDUSTRY -> "industry"
        Category.ANIMALS -> "animals"
    }
}

