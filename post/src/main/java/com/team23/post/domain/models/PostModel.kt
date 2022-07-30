package com.team23.post.domain.models

import android.graphics.Bitmap

data class PostModel(
    val id: String,
    val text: String,
    val image: Bitmap?,
    val likes: Int,
    val link: String?,
    val tags: List<String>,
    val publishDate: String,
    val ownerId: String,
    val ownerName: String,
    val ownerPicture: Bitmap?
)
