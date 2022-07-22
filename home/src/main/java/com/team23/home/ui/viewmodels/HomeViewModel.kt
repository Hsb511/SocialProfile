package com.team23.home.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.home.domain.models.PostModel
import com.team23.home.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): ViewModel() {
    var isLoading = MutableLiveData(true)
    var posts = MutableLiveData<List<PostModel>>()

    init {
        viewModelScope.launch {
            posts.value = getPostsUseCase.execute()
            isLoading.value = false
        }
    }
}