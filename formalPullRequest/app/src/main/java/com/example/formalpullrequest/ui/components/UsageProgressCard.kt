package com.example.formalpullrequest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formalpullrequest.ui.theme.WEPrimary
import com.example.formalpullrequest.ui.theme.WESurfaceVariant
import com.example.formalpullrequest.ui.theme.WETextPrimary
import com.example.formalpullrequest.ui.theme.WETextSecondary

@Composable
fun UsageProgressCard(
    title: String,
    used: String,
    total: String,
    progress: Float,
    modifier: Modifier = Modifier
) {
    WECard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = WETextPrimary
                )

                Text(
                    text = "$used / $total",
                    style = MaterialTheme.typography.bodyMedium,
                    color = WETextSecondary
                )
            }

            LinearProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .width(100.dp),
                color = WEPrimary,
                trackColor = WESurfaceVariant
            )
        }
    }
}