package com.team23.user.data.mappers

import com.team23.api.models.User
import com.team23.core.extensions.downloadBitmap
import com.team23.core.extensions.fromISO8601ToBirthDate
import com.team23.core.extensions.toRoundBitmap
import com.team23.user.R
import com.team23.user.domain.models.ContactData
import com.team23.user.domain.models.ContactDataCategory
import com.team23.user.domain.models.UserModel
import kotlinx.coroutines.CoroutineDispatcher

suspend fun User.toModel(dispatcher: CoroutineDispatcher) = UserModel(
    name = "$firstName $lastName",
    picture = picture.downloadBitmap(dispatcher)?.toRoundBitmap(),
    genderResId = if (gender == "female") { R.drawable.ic_female } else { R.drawable.ic_male },
    dateOfBirth = dateOfBirth.fromISO8601ToBirthDate(),
    contactData = listOf(
        ContactData(ContactDataCategory.EMAIL, email),
        ContactData(ContactDataCategory.PHONE, phone),
        ContactData(ContactDataCategory.ADDRESS,
            "${location.street} \r\n${location.city} ${location.state} \r\n${location.country}".ifBlank { "" })
    )

)