package com.example.wallpapers.di

import androidx.lifecycle.ViewModel
import com.example.wallpapers.presentation.CategoryDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoryDetailViewModel::class)
    fun bindCategoryDetailViewModel(viewModel: CategoryDetailViewModel): ViewModel
}