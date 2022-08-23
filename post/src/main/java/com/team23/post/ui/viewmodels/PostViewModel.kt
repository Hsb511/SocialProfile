package com.team23.post.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.team23.post.domain.usecases.GetCommentsUseCase
import com.team23.post.domain.usecases.GetPostDataUseCase
import com.team23.post.ui.mappers.toVO
import com.team23.post.ui.viewobjects.CommentVO
import com.team23.post.ui.viewobjects.PostVO
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PostViewModel @AssistedInject constructor(
    application: Application,
    @Assisted private val postId: String?,
    private val getPostDataUseCase: GetPostDataUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {
    var post = MutableLiveData<PostVO>()
    var comments = MutableLiveData<List<CommentVO>>()
    var isLoading = MutableLiveData(true)

    init {
        if (postId != null) {
            viewModelScope.launch {
                post.value = getPostDataUseCase.execute(postId)?.toVO()?.also {
                    Glide.with(application.applicationContext)
                        .load(it.postPicture)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .preload()
                    Glide.with(application.applicationContext)
                        .load(it.userPicture)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .preload(40, 40)
                }
                isLoading.value = false
            }
            viewModelScope.launch {
                comments.value = getCommentsUseCase.execute(postId).map {
                    Glide.with(application.applicationContext)
                        .load(it.userPictureUrl)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .preload(40, 40)
                    it.toVO()
                }
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