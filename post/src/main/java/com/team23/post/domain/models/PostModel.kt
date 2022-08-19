package com.team23.post.domain.models

data class PostModel(
    val id: String,
    val text: String,
    val image: String,
    val likes: Int,
    val link: String?,
    val tags: List<String>,
    val publishDate: String,
    val ownerId: String,
    val ownerName: String,
    val ownerPicture: String
)
