package com.example.securityrelease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.securityrelease.ui.PostScreen
import com.example.securityrelease.ui.theme.SecurityReleaseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContent {

            SecurityReleaseTheme {

                PostScreen()
            }
        }
    }
}