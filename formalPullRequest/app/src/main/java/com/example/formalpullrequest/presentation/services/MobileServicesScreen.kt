package com.example.formalpullrequest.presentation.services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formalpullrequest.ui.theme.WETextPrimary
import com.example.formalpullrequest.ui.theme.WETextSecondary

@Composable
fun MobileServicesScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Mobile Services",
            color = WETextPrimary
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Manage your mobile line, bundles and packages.",
            color = WETextSecondary
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Your Mobile Line",
            color = WETextPrimary
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Phone number and current mobile plan",
            color = WETextSecondary
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Available actions",
            color = WETextPrimary
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "• Manage bundles\n• Renew package\n• View plan details\n• Check remaining data",
            color = WETextSecondary
        )
    }
}