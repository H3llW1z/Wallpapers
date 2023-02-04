package com.example.wallpapers.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wallpapers.databinding.FragmentCategoriesListBinding
import com.example.wallpapers.domain.Category

class CategoriesListFragment : Fragment() {

    private var _binding: FragmentCategoriesListBinding? = null
    private val binding: FragmentCategoriesListBinding
        get() = _binding ?: throw RuntimeException("FragmentCategoriesListBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListeners() {
        binding.cardViewAnimals.setOnClickListener {
            launchCategoryDetailFragment(Category.ANIMALS)
        }
        binding.cardViewScience.setOnClickListener {
            launchCategoryDetailFragment(Category.SCIENCE)
        }
        binding.cardViewFeelings.setOnClickListener {
            launchCategoryDetailFragment(Category.FEELINGS)
        }
        binding.cardViewReligion.setOnClickListener {
            launchCategoryDetailFragment(Category.RELIGION)
        }
        binding.cardViewNature.setOnClickListener {
            launchCategoryDetailFragment(Category.NATURE)
        }
        binding.cardViewIndustry.setOnClickListener {
            launchCategoryDetailFragment(Category.INDUSTRY)
        }
        binding.cardViewFood.setOnClickListener {
            launchCategoryDetailFragment(Category.FOOD)
        }
        binding.cardViewPeople.setOnClickListener {
            launchCategoryDetailFragment(Category.PEOPLE)
        }
    }

    private fun launchCategoryDetailFragment(category: Category) {
        findNavController().navigate(
            CategoriesListFragmentDirections.actionCategoriesListFragmentToCategoryDetailFragment(
                category
            )
        )
    }
}