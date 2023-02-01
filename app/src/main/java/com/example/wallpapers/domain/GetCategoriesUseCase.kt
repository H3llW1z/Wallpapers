package com.example.wallpapers.domain

class GetCategoriesUseCase(private val repository: WallpapersRepository) {
    operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}