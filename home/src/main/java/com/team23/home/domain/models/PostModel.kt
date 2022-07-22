package com.team23.home.domain.models

import java.time.LocalDateTime

data class PostModel (
    val text: String,
    val image: String,
    val likes: Int,
    val tags: List<String>,
    val publishDate: LocalDateTime,
    val ownerId: String,
    val ownerName: String
)
