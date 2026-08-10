package com.example.formalpullrequest.presentation.activity

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formalpullrequest.ui.components.WECard
import com.example.formalpullrequest.ui.components.WESectionTitle
import com.example.formalpullrequest.ui.theme.WEBackground
import com.example.formalpullrequest.ui.theme.WESuccess
import com.example.formalpullrequest.ui.theme.WETextPrimary
import com.example.formalpullrequest.ui.theme.WETextSecondary

private data class ActivityItem(
    val title: String,
    val date: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun ActivityScreen() {

    val activities = listOf(
        ActivityItem(
            title = "Payment completed",
            date = "Today",
            description = "Your monthly bill was successfully paid.",
            icon = Icons.Default.Payment
        ),
        ActivityItem(
            title = "Package renewed",
            date = "Yesterday",
            description = "Your current package was renewed.",
            icon = Icons.Default.CheckCircle
        ),
        ActivityItem(
            title = "Service updated",
            date = "3 days ago",
            description = "Your account service configuration was updated.",
            icon = Icons.Default.SwapHoriz
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WEBackground)
            .padding(20.dp)
    ) {

        WESectionTitle(
            title = "Activity"
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(activities) { activity ->

                WECard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = activity.icon,
                            contentDescription = null,
                            tint = WESuccess
                        )

                        Spacer(
                            modifier = Modifier.padding(8.dp)
                        )

                        Column {
                            Text(
                                text = activity.title,
                                color = WETextPrimary
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = activity.description,
                                color = WETextSecondary
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = activity.date,
                                color = WETextSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}