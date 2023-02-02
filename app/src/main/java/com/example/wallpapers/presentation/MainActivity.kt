package com.example.wallpapers.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wallpapers.R
import com.example.wallpapers.data.implementation.WallpapersRepositoryImpl
import com.example.wallpapers.domain.Category
import com.example.wallpapers.domain.GetImagesByCategoryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}