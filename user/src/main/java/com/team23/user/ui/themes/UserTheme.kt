package com.team23.user.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

/**
 * Creating a custom theme with custom colors and typography. Handling the light/dark mode switch
 */
@Composable
fun UserTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val userColors = if (darkTheme) { userDarkColors() } else { userLightColors() }

    MaterialTheme(
        colors = userColors,
        typography = userTypography,
        shapes = Shapes(),
        content = content
    )
}