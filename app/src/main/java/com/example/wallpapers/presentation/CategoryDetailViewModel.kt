package com.example.wallpapers.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapers.domain.Category
import com.example.wallpapers.domain.ErrorEntity
import com.example.wallpapers.domain.GetImagesByCategoryUseCase
import com.example.wallpapers.domain.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryDetailViewModel @Inject constructor(
    private val getImagesByCategoryUseCase: GetImagesByCategoryUseCase
) : ViewModel() {


    private val _state: MutableLiveData<State> = MutableLiveData()

    val state: LiveData<State>
        get() = _state

    fun load(category: Category) {
        if (state.value !is State.Progress && state.value !is State.Success) {
            _state.value = State.Progress
            viewModelScope.launch {
                when (val result = getImagesByCategoryUseCase(category)) {
                    is Result.Error -> when (result.error) {
                        is ErrorEntity.NetworkFailure -> _state.value = State.NetworkError
                        is ErrorEntity.ServerError -> _state.value = State.ServerError
                    }
                    is Result.Success -> _state.value = State.Success(result.data)
                }
            }
        }
    }
}