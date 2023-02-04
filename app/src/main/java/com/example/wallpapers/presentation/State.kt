package com.example.wallpapers.presentation

import com.example.wallpapers.domain.Wallpaper

sealed class State {
    object NetworkError : State()
    object ServerError : State()
    object Progress: State()
    data class Success(val data: List<Wallpaper>): State()
}


