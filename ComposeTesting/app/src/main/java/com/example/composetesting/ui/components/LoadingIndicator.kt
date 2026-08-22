package com.example.composetesting.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {

    CircularProgressIndicator(
        modifier = modifier.semantics {
            contentDescription = "Loading"
        }
    )
}