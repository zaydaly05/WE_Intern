package com.example.formalpullrequest.presentation.usage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formalpullrequest.ui.components.WECard
import com.example.formalpullrequest.ui.components.WESectionTitle
import com.example.formalpullrequest.ui.theme.WEBackground
import com.example.formalpullrequest.ui.theme.WEPrimary
import com.example.formalpullrequest.ui.theme.WETextPrimary
import com.example.formalpullrequest.ui.theme.WETextSecondary

@Composable
fun UsageScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WEBackground)
            .padding(20.dp)
    ) {

        WESectionTitle(
            title = "Usage"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Keep track of your current consumption.",
            color = WETextSecondary
        )

        Spacer(modifier = Modifier.height(20.dp))

        UsageCard(
            title = "Mobile Data",
            used = "18.4 GB",
            total = "30 GB",
            progress = 0.61f
        )

        Spacer(modifier = Modifier.height(12.dp))

        UsageCard(
            title = "Voice Minutes",
            used = "320 min",
            total = "500 min",
            progress = 0.64f
        )

        Spacer(modifier = Modifier.height(12.dp))

        UsageCard(
            title = "SMS",
            used = "74",
            total = "200",
            progress = 0.37f
        )
    }
}

@Composable
private fun UsageCard(
    title: String,
    used: String,
    total: String,
    progress: Float
) {
    WECard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = WETextPrimary
            )

            Text(
                text = "$used / $total",
                color = WETextSecondary
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(),
            color = WEPrimary
        )
    }
}