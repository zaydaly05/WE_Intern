package com.example.coroutinesandrooms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coroutinesandrooms.ui.theme.PostScreen
import com.example.coroutinesandrooms.ui.theme.CoroutinesAndRoomsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CoroutinesAndRoomsTheme {
                PostScreen()
            }
        }
    }
}