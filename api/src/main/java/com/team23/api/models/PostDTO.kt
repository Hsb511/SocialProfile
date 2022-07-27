package com.team23.api.models

data class PostDTO(
    val id: String,
    val text: String,
    val image: String,
    val likes: Int,
    val link: String,
    val tags: List<String>,
    val publishDate: String,
    val owner: UserPreviewDTO
)
