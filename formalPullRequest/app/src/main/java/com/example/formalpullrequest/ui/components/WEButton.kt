package com.example.formalpullrequest.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formalpullrequest.ui.theme.WEPrimary
import com.example.formalpullrequest.ui.theme.WETextOnPrimary

@Composable
fun WEButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = WEPrimary,
            contentColor = WETextOnPrimary
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp)
    ) {
        Text(text = text)
    }
}