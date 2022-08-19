package com.team23.user.domain.models

import java.time.LocalDateTime

data class UserModel(
    val name: String,
    val picture: String?,
    val gender: String,
    val dateOfBirth: LocalDateTime,
    val contactData: List<ContactData>
)
