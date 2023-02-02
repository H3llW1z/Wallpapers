package com.example.wallpapers.data.api.model

import com.google.gson.annotations.SerializedName

data class WallpapersResponse(
    @SerializedName("hits")
    val hits: List<WallpaperDto>
)