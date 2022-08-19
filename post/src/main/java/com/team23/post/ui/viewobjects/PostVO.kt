package com.team23.post.ui.viewobjects

data class PostVO (
    val postPicture: String,
    val userPicture: String,
    val username: String,
    val userId: String,
    val postDate: String,
    val postDescription: String,
    val likesAmount: Int,
    val tags: List<String>
)
