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
fun InternetScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Internet",
            color = WETextPrimary
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "View and manage your home internet services.",
            color = WETextSecondary
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Home Internet",
            color = WETextPrimary
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Manage your internet package and connection.",
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
            text = "• View internet plan\n• Check usage\n• Renew package\n• View account details",
            color = WETextSecondary
        )
    }
}