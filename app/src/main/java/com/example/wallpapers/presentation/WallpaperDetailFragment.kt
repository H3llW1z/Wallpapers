package com.example.wallpapers.presentation

import android.app.WallpaperManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.wallpapers.R
import com.example.wallpapers.databinding.FragmentWallpaperDetailBinding
import com.squareup.picasso.Picasso

class WallpaperDetailFragment : Fragment() {

    private val args by navArgs<WallpaperDetailFragmentArgs>()

    private var _binding: FragmentWallpaperDetailBinding? = null

    private val binding: FragmentWallpaperDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentWallpaperDetailBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWallpaperDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage()
        prepareSetWallpaperButton()
    }

    private fun loadImage() {
        val picasso = Picasso.get()
        picasso.load(args.imageUrl)
            .placeholder(R.drawable.wallpaper_placeholder)
            .error(R.drawable.wallpaper_error_placeholder)
            .into(binding.imageViewWallpaperPreview)
    }

    private fun prepareSetWallpaperButton() {
        binding.buttonSetWallpaper.setOnClickListener {
            val wallpaperManager =
                WallpaperManager.getInstance(requireActivity().applicationContext)
            val bitmap = binding.imageViewWallpaperPreview.drawable.toBitmap()
            wallpaperManager.setBitmap(bitmap)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}