package com.example.formalpullrequest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val WEColorScheme = lightColorScheme(
    primary = WEPrimary,
    onPrimary = WETextOnPrimary,
    secondary = WESecondary,
    background = WEBackground,
    surface = WESurface,
    surfaceVariant = WESurfaceVariant,
    onBackground = WETextPrimary,
    onSurface = WETextPrimary
)

@Composable
fun FormalPullRequestTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WEColorScheme,
        typography = WETypography,
        content = content
    )
}