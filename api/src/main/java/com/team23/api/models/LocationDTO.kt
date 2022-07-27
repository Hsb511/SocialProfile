package com.team23.api.models

data class LocationDTO(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val timeZone: String
)
