package com.team23.post.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.team23.post.domain.usecases.GetPostDataUseCase
import com.team23.post.ui.viewobjects.PostVO
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PostViewModel @AssistedInject constructor(
    @Assisted private val postId: String?,
    private val getPostDataUseCase: GetPostDataUseCase
): ViewModel() {
    var post = MutableLiveData<PostVO>()
    var isLoading = MutableLiveData(true)

    init {
        if (postId != null) {
            viewModelScope.launch {
                post.value = getPostDataUseCase.execute(postId)
                isLoading.value = false
            }
        } else {
            // TODO SHOW ERROR
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(postId: String?): PostViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            postId: String?
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return assistedFactory.create(postId) as T
            }
        }
    }
}