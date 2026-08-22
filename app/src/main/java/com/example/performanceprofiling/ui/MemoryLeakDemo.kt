package com.example.performanceprofiling.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private const val TAG = "MemoryLeakDemo"

/**
 * A large object that we will intentionally keep referenced
 * while a background task is running.
 */
class LargeMemoryObject {

    val data = ByteArray(10 * 1024 * 1024)

    fun doWork() {
        Log.d(TAG, "LargeMemoryObject is doing work")
    }
}

@Composable
fun MemoryLeakDemo() {

    var isRunning by remember {
        mutableStateOf(false)
    }

    var largeObject by remember {
        mutableStateOf<LargeMemoryObject?>(null)
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Memory Leak Demo",
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge
            )

            Text(
                text = if (isRunning) {
                    "Background task is running..."
                } else {
                    "Background task is stopped."
                }
            )

            Button(
                onClick = {

                    if (!isRunning) {

                        largeObject = LargeMemoryObject()

                        isRunning = true

                    } else {

                        isRunning = false
                    }
                }
            ) {

                Text(
                    text = if (isRunning) {
                        "Stop Task"
                    } else {
                        "Start Leak"
                    }
                )
            }

            /*
             * INTENTIONAL PROBLEM
             *
             * The coroutine is started whenever isRunning becomes true.
             *
             * The LargeMemoryObject is captured by the coroutine.
             *
             * We will later improve this implementation and make
             * the lifetime of the work correctly follow the UI lifecycle.
             */
            if (isRunning) {

                val capturedObject = largeObject

                LaunchedEffect(Unit) {

                    while (true) {

                        delay(1000)

                        capturedObject?.doWork()

                        Log.d(
                            TAG,
                            "Background task is still running"
                        )
                    }
                }
            }

            /*
             * This effect demonstrates lifecycle cleanup.
             *
             * It logs when this composable leaves the composition.
             */
            DisposableEffect(Unit) {

                Log.d(
                    TAG,
                    "MemoryLeakDemo entered composition"
                )

                onDispose {

                    Log.d(
                        TAG,
                        "MemoryLeakDemo left composition"
                    )
                }
            }
        }
    }
}