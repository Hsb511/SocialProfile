package com.team23.home.ui.viewmodels

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.team23.home.domain.usecases.GetPostsUseCase
import com.team23.home.ui.viewobjects.PostVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    var isLoading = MutableLiveData(true)
    var posts: LiveData<List<PostVO>> = liveData {
        emit(
            getPostsUseCase.execute().map {
                PostVO(
                    id = it.id,
                    text = it.text,
                    imageUri = it.imageUrl.toUri().buildUpon().scheme("https").build(),
                    publishDate = it.publishDate,
                    ownerId = it.ownerId,
                    ownerName = it.ownerName,
                    ownerPictureUri = it.ownerPictureUrl.toUri().buildUpon().scheme("https").build()
                )
            }
        )
        isLoading.value = false

    }
}