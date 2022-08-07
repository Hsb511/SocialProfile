package com.team23.home.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.team23.home.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    var isLoading = MutableLiveData(true)
    var posts = liveData {
        getPostsUseCase.execute()
            .onStart { isLoading.value = true }
            .collect {
                isLoading.value = false
                emit(it)
            }
    }
}