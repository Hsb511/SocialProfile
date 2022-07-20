package com.team23.api.models

data class PostPreview(
    val id: String,
    val text: String,
    val image: String,
    val likes: Int,
    val tags: List<String>,
    val publishDate: String,
    val owner: UserPreview
)