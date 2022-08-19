package com.team23.home.domain.models

data class PostModel (
    val id: String,
    val text: String,
    val imageUrl: String,
    val likes: Int,
    val tags: List<String>,
    val publishDate: String,
    val ownerId: String,
    val ownerName: String,
    val ownerPictureUrl: String
)
