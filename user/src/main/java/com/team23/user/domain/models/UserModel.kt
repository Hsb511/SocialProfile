package com.team23.user.domain.models

import android.graphics.Bitmap

data class UserModel(
    val name: String,
    val picture: Bitmap?,
    val genderResId: Int,
    val dateOfBirth: String,
    val contactData: List<ContactData>
)
