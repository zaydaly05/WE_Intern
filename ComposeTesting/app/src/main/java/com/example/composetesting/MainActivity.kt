package com.example.composetesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetesting.navigation.AppNavigation
import com.example.composetesting.ui.theme.ComposeTestingTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTestingTheme {
                AppNavigation()
            }
        }
    }
}