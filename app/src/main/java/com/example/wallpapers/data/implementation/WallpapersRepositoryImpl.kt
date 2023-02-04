package com.example.wallpapers.data.implementation

import com.example.wallpapers.data.api.ApiFactory
import com.example.wallpapers.data.mappers.mapCategoryToCategoryName
import com.example.wallpapers.data.mappers.toEntity
import com.example.wallpapers.domain.*

class WallpapersRepositoryImpl : WallpapersRepository {

    private val apiService = ApiFactory.apiService

    override suspend fun getWallpapersByCategory(category: Category): Result<List<Wallpaper>> {
        try {
            val response = apiService.getWallpapersByCategory(mapCategoryToCategoryName(category))

            if (!response.isSuccessful) {
                return mapHttpCodeToErrorEntity(response.code())
            }
            val wallpapers = (response.body()?.hits ?: emptyList()).map { it.toEntity() }
            return Result.Success(wallpapers)
        } catch (e: Exception) {
            return Result.Error(ErrorEntity.NetworkFailure)
        }
    }

    private fun mapHttpCodeToErrorEntity(code: Int): Result<List<Wallpaper>> {
        val errorEntity = when (code) {
            401, 403 -> throw RuntimeException("Auth error")
            in 400..451 -> throw RuntimeException("Client error")
            in 500..526 -> ErrorEntity.ServerError
            else -> ErrorEntity.NetworkFailure
        }
        return Result.Error(errorEntity)
    }
}