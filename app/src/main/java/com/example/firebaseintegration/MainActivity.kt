package com.example.firebaseintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    companion object {

        const val FIREBASE_TEST_EMAIL = "zaydaly0502@gmail.com"
        const val FIREBASE_TEST_PASSWORD = "Dodo,2005"
    }

    private lateinit var analyticsHelper: FirebaseAnalyticsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analyticsHelper = FirebaseAnalyticsHelper(this)

        analyticsHelper.logScreenOpened("Firebase Test Screen")

        setContent {
            MaterialTheme {
                FirebaseTestScreen(
                    onAnalyticsClick = {
                        analyticsHelper.logButtonClicked("Analytics Button")
                    },
                    onCrashClick = {
                        analyticsHelper.logButtonClicked("Test Crash")

                        throw RuntimeException(
                            "Firebase Crashlytics test crash"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun FirebaseTestScreen(
    onAnalyticsClick: () -> Unit,
    onCrashClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Firebase Integration",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Analytics + Crashlytics",
            modifier = Modifier.padding(top = 8.dp)
        )

        // Firebase test account
        Text(
            text = "Test Email: ${MainActivity.FIREBASE_TEST_EMAIL}",
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = "Test Password: ${MainActivity.FIREBASE_TEST_PASSWORD}",
            modifier = Modifier.padding(top = 4.dp)
        )

        Button(
            onClick = onAnalyticsClick,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text("Log Analytics Event")
        }

        Button(
            onClick = onCrashClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Test Crash")
        }
    }
}