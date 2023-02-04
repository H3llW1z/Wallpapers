package com.example.wallpapers.di

import com.example.wallpapers.data.api.ApiFactory
import com.example.wallpapers.data.api.ApiService
import com.example.wallpapers.data.implementation.WallpapersRepositoryImpl
import com.example.wallpapers.domain.WallpapersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindWallpapersRepository(impl: WallpapersRepositoryImpl): WallpapersRepository


    companion object {
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}