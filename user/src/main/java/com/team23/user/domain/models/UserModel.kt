package com.team23.user.domain.models

import android.graphics.Bitmap
import java.time.LocalDateTime

data class UserModel(
    val name: String,
    val picture: Bitmap?,
    val gender: String,
    val dateOfBirth: LocalDateTime,
    val contactData: List<ContactData>
)
