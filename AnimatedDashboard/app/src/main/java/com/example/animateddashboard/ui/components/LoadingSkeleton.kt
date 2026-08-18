package com.example.animateddashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingSkeleton() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            ShimmerBox(
                height = 16.dp
            )

            ShimmerBox(
                height = 30.dp
            )

            ShimmerBox(
                height = 18.dp
            )

            ShimmerBox(
                height = 18.dp
            )

            ShimmerBox(
                height = 18.dp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            ShimmerBox(
                height = 16.dp
            )
        }
    }
}