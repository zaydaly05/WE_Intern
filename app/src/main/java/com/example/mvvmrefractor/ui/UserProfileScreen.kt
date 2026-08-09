package com.example.mvvmrefractor.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserProfileScreen(
    viewModel: UserProfileViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = uiState.name,
            onValueChange = {
                viewModel.onNameChanged(it)
            },
            label = {
                Text("Enter your name")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Hello ${uiState.name}"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.onButtonClicked()
            }
        ) {
            Text("Increase Counter")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Clicked ${uiState.clickCount} times"
        )
    }
}