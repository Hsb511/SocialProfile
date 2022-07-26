package com.team23.user.ui.viewobjects

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import com.team23.user.domain.models.ContactData

data class UserVO(
    val name: String,
    val picture: Bitmap?,
    val backgroundColor: Color,
    val genderResId: Int,
    val dateOfBirth: String,
    val contactData: List<ContactData>
)
