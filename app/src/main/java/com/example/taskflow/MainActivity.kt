package com.example.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskflow.data.repository.UserRepositoryImpl
import com.example.taskflow.domain.repository.UserRepository
import com.example.taskflow.presentation.UserUiState
import com.example.taskflow.presentation.UserViewModel
import com.example.taskflow.presentation.UserViewModelFactory
import com.example.taskflow.ui.theme.TaskFlowTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TaskFlowTheme {
                TaskFlowScreen(
                    repository = UserRepositoryImpl()
                )
            }
        }
    }
}

@Composable
fun TaskFlowScreen(
    repository: UserRepository
) {
    val factory = UserViewModelFactory(repository)

    val viewModel: UserViewModel = viewModel(
        factory = factory
    )

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (val state = uiState) {

            is UserUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.testTag("loading_indicator")
                )
            }

            is UserUiState.Success -> {
                Text(
                    text = "Tasks: ${state.users.size}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            is UserUiState.Error -> {
                Text(
                    text = "Error: ${state.message}",
                    modifier = Modifier.testTag("error_message"),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}