package com.example.formalpullrequest.presentation.support

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formalpullrequest.ui.components.WECard
import com.example.formalpullrequest.ui.components.WESectionTitle
import com.example.formalpullrequest.ui.theme.WEBackground
import com.example.formalpullrequest.ui.theme.WEPrimary
import com.example.formalpullrequest.ui.theme.WETextPrimary
import com.example.formalpullrequest.ui.theme.WETextSecondary

@Composable
fun SupportScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WEBackground)
            .padding(20.dp)
    ) {

        WESectionTitle(
            title = "Support"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "We're here to help.",
            color = WETextSecondary
        )

        Spacer(modifier = Modifier.height(20.dp))

        SupportOption(
            title = "Help Center",
            description = "Find answers to frequently asked questions.",
            icon = Icons.Default.HelpOutline
        )

        Spacer(modifier = Modifier.height(12.dp))

        SupportOption(
            title = "Live Chat",
            description = "Chat with a WE support representative.",
            icon = Icons.Default.Chat
        )

        Spacer(modifier = Modifier.height(12.dp))

        SupportOption(
            title = "Contact Support",
            description = "Speak directly with customer support.",
            icon = Icons.Default.Phone
        )
    }
}

@Composable
private fun SupportOption(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    WECard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = WEPrimary
            )

            Spacer(
                modifier = Modifier.padding(8.dp)
            )

            Column {
                Text(
                    text = title,
                    color = WETextPrimary
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = description,
                    color = WETextSecondary
                )
            }
        }
    }
}