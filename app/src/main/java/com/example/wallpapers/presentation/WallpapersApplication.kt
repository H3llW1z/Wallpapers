package com.example.wallpapers.presentation

import android.app.Application
import com.example.wallpapers.di.ApplicationComponent
import com.example.wallpapers.di.DaggerApplicationComponent

class WallpapersApplication: Application() {
    val component: ApplicationComponent = DaggerApplicationComponent.create()
}