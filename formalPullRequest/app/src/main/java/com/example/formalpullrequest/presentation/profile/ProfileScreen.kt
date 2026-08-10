package com.example.formalpullrequest.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formalpullrequest.ui.components.WECard
import com.example.formalpullrequest.ui.components.WESectionTitle
import com.example.formalpullrequest.ui.theme.WEBackground
import com.example.formalpullrequest.ui.theme.WETextPrimary
import com.example.formalpullrequest.ui.theme.WETextSecondary

@Composable
fun ProfileScreen(
    onLogout: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WEBackground)
            .padding(20.dp)
    ) {

        WESectionTitle(
            title = "Profile"
        )

        Spacer(modifier = Modifier.height(20.dp))

        WECard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Customer",
                color = WETextSecondary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "WE Customer",
                color = WETextPrimary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Phone number",
                color = WETextSecondary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "010 0000 0000",
                color = WETextPrimary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Email",
                color = WETextSecondary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "customer@example.com",
                color = WETextPrimary
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Logout")
        }
    }
}