package com.example.formalpullrequest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formalpullrequest.ui.theme.WESuccess
import com.example.formalpullrequest.ui.theme.WESurfaceVariant

@Composable
fun WEStatusChip(
    text: String
) {
    Text(
        text = text,
        color = WESuccess,
        modifier = Modifier
            .background(
                color = WESurfaceVariant,
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
    )
}