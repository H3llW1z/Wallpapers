package com.example.wallpapers.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpapers.R
import com.example.wallpapers.databinding.FragmentCategoryDetailBinding
import com.example.wallpapers.presentation.adapters.WallpapersAdapter
import com.google.android.material.snackbar.Snackbar

class CategoryDetailFragment : Fragment() {

    private val args by navArgs<CategoryDetailFragmentArgs>()

    private var _binding: FragmentCategoryDetailBinding? = null

    private val binding: FragmentCategoryDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCategoryDetailBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[CategoryDetailViewModel::class.java]
    }

    private val adapter = WallpapersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupStateObservation()
        viewModel.load(args.category)
    }

    private fun launchWallpaperDetailFragment(imageUrl: String) {
        findNavController().navigate(
            CategoryDetailFragmentDirections.actionCategoryDetailFragmentToWallpaperDetailFragment(
                imageUrl
            )
        )
    }

    private fun setupRecyclerView() {
        adapter.onItemClickListener = {
            launchWallpaperDetailFragment(it.largeImageUrl)
        }
        binding.recyclerViewWallpapers.adapter = adapter
        binding.recyclerViewWallpapers.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setupStateObservation() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is State.Progress -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is State.NetworkError -> {
                    binding.progressBar.visibility = View.GONE
                    showNetworkErrorSnackbar()
                }
                is State.ServerError -> {
                    binding.progressBar.visibility = View.GONE
                    showServerErrorSnackbar()
                }
                is State.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(it.data)
                }
            }
        }
    }

    private fun showServerErrorSnackbar() {
        val snackbar = Snackbar.make(
            binding.root,
            getString(R.string.server_error_ocurred),
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction(getString(R.string.try_again)) {
            viewModel.load(args.category)
        }
        snackbar.show()
    }

    private fun showNetworkErrorSnackbar() {
        val snackbar = Snackbar.make(
            binding.root,
            getString(R.string.check_internet_connection),
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction(getString(R.string.try_again)) {
            viewModel.load(args.category)
        }
        snackbar.show()
    }
}