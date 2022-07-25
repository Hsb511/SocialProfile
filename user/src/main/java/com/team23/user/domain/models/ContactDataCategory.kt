package com.team23.user.domain.models

import com.team23.user.R

enum class ContactDataCategory(val resId: Int) {
    EMAIL(R.string.user_profile_contact_email),
    PHONE(R.string.user_profile_contact_phone),
    ADDRESS(R.string.user_profile_contact_address)
}