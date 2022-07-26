package com.team23.user.ui.viewobjects

import android.graphics.Bitmap
import com.team23.user.domain.models.ContactData

data class UserVO(
    val name: String,
    val picture: Bitmap?,
    val backgroundResId: Int,
    val genderResId: Int,
    val dateOfBirth: String,
    val contactData: List<ContactData>
)
