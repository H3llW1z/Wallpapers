package com.example.wallpapers.presentation

import android.app.WallpaperManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.wallpapers.R
import com.example.wallpapers.databinding.FragmentWallpaperDetailBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class WallpaperDetailFragment : Fragment() {

    private val args by navArgs<WallpaperDetailFragmentArgs>()

    private var _binding: FragmentWallpaperDetailBinding? = null

    private val binding: FragmentWallpaperDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentWallpaperDetailBinding is null")

    private var imageLoaded = false
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
            .into(binding.imageViewWallpaperPreview, object: Callback {
                override fun onSuccess() {
                    imageLoaded = true
                }

                override fun onError(e: Exception?) {
                }
            })
    }

    private fun prepareSetWallpaperButton() {
        binding.buttonSetWallpaper.setOnClickListener {
            if (imageLoaded) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val wallpaperManager =
                        WallpaperManager.getInstance(requireActivity().applicationContext)
                    val bitmap = binding.imageViewWallpaperPreview.drawable.toBitmap()
                    wallpaperManager.setBitmap(bitmap)
                    withContext(Dispatchers.Main) {
                        showWallpaperSetToast()
                    }
                }
            } else {
                showWaitImageToLoadToast()
            }
        }
    }
    private fun showWallpaperSetToast() {
        Toast.makeText(requireContext(), getString(R.string.wallpapers_loaded_successfully), Toast.LENGTH_SHORT).show()
    }

    private fun showWaitImageToLoadToast() {
        Toast.makeText(requireContext(), getString(R.string.image_not_loaded_yet), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}