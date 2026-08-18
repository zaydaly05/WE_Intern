package com.example.animateddashboard.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.animateddashboard.model.DashboardData

@Composable
fun StatusCard(
    data: DashboardData
) {

    val transition = updateTransition(
        targetState = true,
        label = "post_transition"
    )

    val alpha = transition.animateFloat(
        transitionSpec = {
            tween(500)
        },
        label = "post_alpha"
    ) { targetState ->
        if (targetState) 1f else 0f
    }

    val titleColor = transition.animateColor(
        transitionSpec = {
            tween(500)
        },
        label = "title_color"
    ) { targetState ->
        if (targetState) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alpha.value),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Post #${data.id}",
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = data.title,
                color = titleColor.value,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = data.body,
                style = MaterialTheme.typography.bodyLarge
            )

            StatusRow(
                label = "User ID",
                value = data.userId.toString()
            )
        }
    }
}

@Composable
private fun StatusRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = label)

        Text(text = value)
    }
}