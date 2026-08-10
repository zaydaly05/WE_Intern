package com.example.formalpullrequest.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.formalpullrequest.ui.theme.WETextPrimary

@Composable
fun WESectionTitle(
    title: String
) {
    Text(
        text = title,
        color = WETextPrimary,
        fontSize = 22.sp
    )
}