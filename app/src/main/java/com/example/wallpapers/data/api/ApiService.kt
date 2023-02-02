package com.example.wallpapers.data.api

import com.example.wallpapers.data.api.model.WallpapersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("api/")
    suspend fun getWallpapersByCategory(
        @Query(QUERY_PARAM_CATEGORY) category: String,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_IMAGE_TYPE) imageType: String = PHOTO,
        @Query(QUERY_PARAM_ORIENTATION) orientation: String = ORIENTATION_VERTICAL,
        @Query(QUERY_PARAM_PER_PAGE) perPage: Int = PER_PAGE_LIMIT,
    ): Response<WallpapersResponse>

    companion object {
        private const val QUERY_PARAM_API_KEY = "key"
        private const val QUERY_PARAM_ORIENTATION = "orientation"
        private const val QUERY_PARAM_CATEGORY = "category"
        private const val QUERY_PARAM_IMAGE_TYPE = "image_type"
        private const val QUERY_PARAM_PER_PAGE = "per_page"

        private const val API_KEY = "33106230-b104905cd7ff74ed17e2229af"
        private const val PER_PAGE_LIMIT = 200
        private const val ORIENTATION_VERTICAL = "vertical"
        private const val PHOTO = "photo"
    }
}