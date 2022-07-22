package com.team23.home.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.home.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): ViewModel() {
    var isLoading = MutableLiveData(true)

    init {
        viewModelScope.launch {
            if (getPostsUseCase.execute().isNotEmpty()) {
                isLoading.value = false
            }
        }
    }
}