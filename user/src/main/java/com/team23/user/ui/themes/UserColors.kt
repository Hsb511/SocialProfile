package com.team23.user.ui.themes

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.team23.core.R

/**
 * Using the onSurface color as a variant of the background color: lighter than black
 */
@Composable
fun userLightColors() = lightColors(
    primary = colorResource(id = R.color.lapis_lazuli),
    primaryVariant = colorResource(id = R.color.indigo_dye),
    onSurface = colorResource(id = R.color.dim_gray)
)

/**
 * Using the onSurface color as a variant of the background color: darker than white
 */
@Composable
fun userDarkColors() = darkColors(
    primary = colorResource(id = R.color.light_steel_blue),
    primaryVariant = colorResource(id = R.color.lapis_lazuli),
    onSurface = colorResource(id = R.color.medium_gray)
)