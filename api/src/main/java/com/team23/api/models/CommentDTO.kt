package com.team23.api.models

data class CommentDTO(
    val id: String,
    val message: String,
    val owner: UserPreviewDTO,
    val post: String,
    val publishDate: String
)
