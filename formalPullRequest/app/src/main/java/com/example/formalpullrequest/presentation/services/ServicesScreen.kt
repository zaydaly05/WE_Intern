package com.example.formalpullrequest.presentation.services

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.SimCard
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

private data class ServiceItem(
    val title: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

@Composable
fun ServicesScreen(
    onServiceClick: (String) -> Unit
) {

    val services = listOf(
        ServiceItem(
            title = "Mobile Services",
            description = "Manage your mobile line, bundles and packages.",
            icon = Icons.Default.PhoneAndroid,
            route = "mobile_services"
        ),
        ServiceItem(
            title = "Internet",
            description = "View and manage your home internet services.",
            icon = Icons.Default.Language,
            route = "internet"
        ),
        ServiceItem(
            title = "Router",
            description = "Monitor and manage your connected router.",
            icon = Icons.Default.Router,
            route = "router"
        ),
        ServiceItem(
            title = "SIM Management",
            description = "Manage your SIM and account services.",
            icon = Icons.Default.SimCard,
            route = "sim_management"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WEBackground)
            .padding(20.dp)
    ) {

        WESectionTitle(
            title = "Services"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Manage your WE services in one place.",
            color = WETextSecondary
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(services) { service ->

                WECard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onServiceClick(service.route)
                        }
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = service.icon,
                            contentDescription = null,
                            tint = WEPrimary
                        )

                        Spacer(
                            modifier = Modifier.padding(8.dp)
                        )

                        Column {

                            Text(
                                text = service.title,
                                color = WETextPrimary
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = service.description,
                                color = WETextSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}