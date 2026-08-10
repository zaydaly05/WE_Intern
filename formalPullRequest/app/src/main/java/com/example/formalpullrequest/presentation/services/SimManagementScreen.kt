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
fun SimManagementScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "SIM Management",
            color = WETextPrimary
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Manage your SIM and account services.",
            color = WETextSecondary
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "SIM Information",
            color = WETextPrimary
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Manage your SIM and mobile account.",
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
            text = "• View SIM information\n• Replace SIM\n• Manage SIM services\n• View account information",
            color = WETextSecondary
        )
    }
}