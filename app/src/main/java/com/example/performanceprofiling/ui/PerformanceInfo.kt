package com.example.performanceprofiling.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PerformanceInfo() {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Profiling Instructions",
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "1. Use the Memory Profiler to observe memory usage."
            )

            Text(
                text = "2. Use the CPU Profiler to find expensive work."
            )

            Text(
                text = "3. Use Layout Inspector to inspect Compose recomposition."
            )

            Text(
                text = "4. Trigger the problems multiple times."
            )

            Text(
                text = "5. Record what you observe before fixing the code."
            )

            Text(
                text = "6. After profiling, we will optimize the application."
            )
        }
    }
}