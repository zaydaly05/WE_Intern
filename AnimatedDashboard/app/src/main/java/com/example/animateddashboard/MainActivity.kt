package com.example.animateddashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.animateddashboard.ui.DashboardScreen
import com.example.animateddashboard.ui.theme.AnimatedDashboardTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AnimatedDashboardTheme {
                DashboardScreen()
            }
        }
    }
}