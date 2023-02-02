package com.example.wallpapers.data.implementation


import com.example.wallpapers.data.api.ApiFactory
import com.example.wallpapers.data.mappers.mapCategoryToCategoryName
import com.example.wallpapers.data.mappers.toEntity
import com.example.wallpapers.domain.Category
import com.example.wallpapers.domain.Wallpaper
import com.example.wallpapers.domain.WallpapersRepository
import com.example.wallpapers.data.implementation.exceptions.AuthorizationErrorException
import com.example.wallpapers.data.implementation.exceptions.ClientErrorException
import com.example.wallpapers.data.implementation.exceptions.NetworkFailureException
import com.example.wallpapers.data.implementation.exceptions.ServerErrorException

class WallpapersRepositoryImpl: WallpapersRepository {

    private val apiService = ApiFactory.apiService

    override suspend fun getWallpapersByCategory(category: Category): List<Wallpaper> {
        val response = apiService.getWallpapersByCategory(mapCategoryToCategoryName(category))
        if (!response.isSuccessful) {
            throw mapHttpCodeToException(response.code())
        }
        val wallpapers = response.body()?.hits ?: emptyList()
        return wallpapers.map {it.toEntity()}
    }

    private fun mapHttpCodeToException(code: Int): Exception {
        return when (code) {
            401, 403 -> AuthorizationErrorException()
            in 400..451 -> ClientErrorException()
            in 500..526 -> ServerErrorException()
            else -> NetworkFailureException()
        }
    }
}