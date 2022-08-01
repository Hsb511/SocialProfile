package com.team23.post.ui.viewobjects

import android.graphics.Bitmap

data class CommentVO(
    val userPicture: Bitmap?,
    val username: String,
    val userId: String,
    val duration: String,
    val text: String
)
