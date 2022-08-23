package com.team23.home.ui.viewobjects

import android.net.Uri

data class PostVO (
    val id: String,
    val text: String,
    val imageUri: Uri,
    val publishDate: String,
    val ownerId: String,
    val ownerName: String,
    val ownerPictureUri: Uri
)