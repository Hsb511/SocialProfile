package com.team23.api.models

data class User(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val gender: String,
    val dateOfBirth: String,
    val email: String,
    val phone: String,
    val location: Location
)
