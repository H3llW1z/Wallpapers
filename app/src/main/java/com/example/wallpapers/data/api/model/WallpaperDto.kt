package com.example.wallpapers.data.api.model

import com.google.gson.annotations.SerializedName

data class WallpaperDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("previewURL")
    val previewURL: String,
    @SerializedName("webformatURL")
    val webformatURL: String,
    @SerializedName("largeImageURL")
    val largeImageURL: String
)