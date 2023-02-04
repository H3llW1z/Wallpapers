package com.example.wallpapers.di

import com.example.wallpapers.presentation.CategoryDetailFragment
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
@ApplicationScope
interface ApplicationComponent {

    fun inject(fragment: CategoryDetailFragment)
}