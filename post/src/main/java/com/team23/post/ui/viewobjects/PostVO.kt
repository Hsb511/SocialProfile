package com.team23.post.ui.viewobjects

import android.graphics.Bitmap

data class PostVO (
    val postPicture: Bitmap?,
    val userPicture: Bitmap?,
    val username: String,
    val userId: String,
    val postDate: String,
    val postDescription: String,
    val likesAmount: Int,
    val tags: List<String>
)
