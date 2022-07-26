package com.team23.user.ui.themes

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

const val LightSteelBlue = 0xFF99B3CC
const val LapisLazuli = 0xFF336699
const val IndigoDye = 0xFF1f3e5c
const val DimGray = 0xFF696969
const val MediumGray = 0xFFBEBEBE

/**
 * Using the onSurface color as a variant of the background color: lighter than black
 */
val UserLightColors = lightColors(
    primary = Color(LapisLazuli),
    primaryVariant = Color(IndigoDye),
    onSurface = Color(DimGray)
)

/**
 * Using the onSurface color as a variant of the background color: darker than white
 */
val UserDarkColors = darkColors(
    primary = Color(LightSteelBlue),
    primaryVariant = Color(LapisLazuli),
    onSurface = Color(MediumGray)
)