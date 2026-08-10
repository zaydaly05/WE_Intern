package com.example.formalpullrequest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.formalpullrequest.presentation.navigation.AppNavigation
import com.example.formalpullrequest.ui.theme.FormalPullRequestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            FormalPullRequestTheme {
                AppNavigation()
            }
        }
    }
}