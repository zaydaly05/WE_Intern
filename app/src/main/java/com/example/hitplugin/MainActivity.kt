package com.example.hitplugin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hitplugin.database.UserEntity
import com.example.hitplugin.ui.UserUiState
import com.example.hitplugin.ui.UserViewModel
import com.example.hitplugin.ui.theme.HitPluginTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            HitPluginTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    UserScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {

        is UserUiState.Loading -> {

            LoadingScreen(
                modifier = modifier
            )
        }

        is UserUiState.Success -> {

            UserSuccessScreen(
                users = state.users,
                modifier = modifier
            )
        }

        is UserUiState.Error -> {

            ErrorScreen(
                message = state.message,
                onRetry = viewModel::loadUsers,
                modifier = modifier
            )
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CircularProgressIndicator()

        Text(
            text = "Loading users...",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun UserSuccessScreen(
    users: List<UserEntity>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Users: ${users.size}"
        )

        users.forEach { user ->

            Text(
                text = "${user.name} - ${user.email}",
                modifier = Modifier.padding(
                    top = 12.dp
                )
            )
        }
    }
}

@Composable
fun ErrorScreen(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Something went wrong"
        )

        Text(
            text = message,
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = onRetry
        ) {
            Text("Retry")
        }
    }
}