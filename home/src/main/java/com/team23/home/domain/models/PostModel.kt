package com.team23.home.domain.models

import android.graphics.Bitmap

data class PostModel (
    val text: String,
    val image: Bitmap?,
    val likes: Int,
    val tags: List<String>,
    val publishDate: String,
    val ownerId: String,
    val ownerName: String,
    val ownerPicture: Bitmap?
)
