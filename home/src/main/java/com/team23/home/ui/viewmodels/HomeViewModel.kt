package com.team23.home.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.team23.home.domain.usecases.GetPostsUseCase
import com.team23.home.ui.viewobjects.PostVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    var isLoading = MutableLiveData(true)
    var posts: LiveData<List<PostVO>> = liveData {
        emit(
            getPostsUseCase.execute().map {
                Glide.with(application.applicationContext)
                    .load(it.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .preload(120, 120)

                Glide.with(application.applicationContext)
                    .load(it.ownerPictureUrl)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .preload(40, 40)
                PostVO(
                    id = it.id,
                    text = it.text,
                    imageUri = it.imageUrl,
                    publishDate = it.publishDate,
                    ownerId = it.ownerId,
                    ownerName = it.ownerName,
                    ownerPictureUri = it.ownerPictureUrl
                )
            }
        )
        isLoading.value = false
    }
}