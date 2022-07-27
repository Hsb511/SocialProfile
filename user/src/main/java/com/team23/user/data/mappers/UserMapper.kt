package com.team23.user.data.mappers

import com.team23.api.models.UserDTO
import com.team23.core.extensions.downloadBitmap
import com.team23.core.extensions.fromISO8601
import com.team23.core.extensions.toRoundBitmap
import com.team23.user.domain.models.ContactData
import com.team23.user.domain.models.ContactDataCategory
import com.team23.user.domain.models.UserModel
import kotlinx.coroutines.CoroutineDispatcher

suspend fun UserDTO.toModel(dispatcher: CoroutineDispatcher) = UserModel(
    name = "$firstName $lastName",
    picture = picture.downloadBitmap(dispatcher)?.toRoundBitmap(),
    gender = gender,
    dateOfBirth = dateOfBirth.fromISO8601(),
    contactData = listOf(
        ContactData(ContactDataCategory.EMAIL, email),
        ContactData(ContactDataCategory.PHONE, phone),
        ContactData(ContactDataCategory.ADDRESS,
            "${location.street} \r\n${location.city} ${location.state} \r\n${location.country}".ifBlank { "" })
    )

)