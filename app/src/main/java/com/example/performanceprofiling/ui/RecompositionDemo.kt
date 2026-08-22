package com.example.performanceprofiling.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tracing.trace

private const val TAG = "RecompositionDemo"

@Composable
fun RecompositionDemo() {

    var counter by remember {
        mutableIntStateOf(0)
    }

    /*
     * INTENTIONAL PERFORMANCE PROBLEM
     *
     * This calculation runs every time this composable
     * recomposes.
     *
     * Button click
     *      ↓
     * counter++
     *      ↓
     * Recomposition
     *      ↓
     * performExpensiveCalculation()
     *      ↓
     * 100,000,000 iterations
     *      ↓
     * High CPU usage
     */

    // IMPORTANT:
    // Do NOT use remember here for the CPU-problem demonstration.
    val expensiveResult = remember {
        performExpensiveCalculation()
    }
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Heavy Recomposition Demo",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Counter: $counter"
            )

            Text(
                text = "Expensive result: $expensiveResult"
            )

            Button(
                onClick = {
                    counter++
                }
            ) {
                Text(
                    text = "Trigger Recomposition"
                )
            }

            Text(
                text = "Every click causes this composable to recompose."
            )

            Text(
                text = "Open Android Studio Profiler and record the CPU."
            )
        }
    }
}


/*
 * INTENTIONAL CPU-HEAVY FUNCTION
 *
 * This function performs a large amount of actual CPU work.
 *
 * The trace label makes it easier to identify in
 * System Trace.
 */
internal fun performExpensiveCalculation(): Long =
    trace("performExpensiveCalculation") {

        Log.d(
            TAG,
            "performExpensiveCalculation() started"
        )

        var result = 0L

        // CPU-intensive work
        for (i in 1..100_000_000) {
            result += (i * 31L) % 97L
        }

        Log.d(
            TAG,
            "performExpensiveCalculation() finished: $result"
        )

        result
    }