package com.example.animateddashboard.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animateddashboard.ui.components.LoadingSkeleton
import com.example.animateddashboard.ui.components.StatusCard

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = "Network Status",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Advanced Compose Animations",
                style = MaterialTheme.typography.bodyMedium
            )

            AnimatedVisibility(
                visible = uiState is UiState.Loading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LoadingSkeleton()
            }

            AnimatedVisibility(
                visible = uiState is UiState.Success,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {

                val data = (uiState as? UiState.Success)?.data

                if (data != null) {
                    StatusCard(data = data)
                }
            }

            AnimatedVisibility(
                visible = uiState is UiState.Error,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut()
            ) {

                val message =
                    (uiState as? UiState.Error)?.message
                        ?: "Unknown error"

                ErrorContent(
                    message = message,
                    onRetry = viewModel::loadData
                )
            }
        }
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = message,
            color = MaterialTheme.colorScheme.error
        )

        Button(
            onClick = onRetry
        ) {
            Text("Retry")
        }
    }
}