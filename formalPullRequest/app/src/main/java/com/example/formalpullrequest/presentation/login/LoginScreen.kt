package com.example.formalpullrequest.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formalpullrequest.ui.theme.WEBackground
import com.example.formalpullrequest.ui.theme.WEPrimary
import com.example.formalpullrequest.ui.theme.WETextPrimary
import com.example.formalpullrequest.ui.theme.WETextSecondary

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    // ------------------------------------------
    // Navigate after successful login
    // ------------------------------------------

    LaunchedEffect(
        state.isLoggedIn,
        state.customerId
    ) {
        val customerId = state.customerId

        if (state.isLoggedIn && customerId != null) {
            onLoginSuccess(customerId)
            viewModel.clearLoginState()
        }
    }

    // ------------------------------------------
    // Screen
    // ------------------------------------------

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WEBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // ------------------------------------------
        // Header
        // ------------------------------------------

        Text(
            text = "Welcome to WE",
            color = WETextPrimary
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Login to your account",
            color = WETextSecondary
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        // ------------------------------------------
        // Phone Number
        // ------------------------------------------

        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = viewModel::onPhoneNumberChanged,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Phone number")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            enabled = !state.isLoading
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // ------------------------------------------
        // Password
        // ------------------------------------------

        OutlinedTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChanged,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Password")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            enabled = !state.isLoading
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // ------------------------------------------
        // Error
        // ------------------------------------------

        state.errorMessage?.let { error ->

            Text(
                text = error,
                color = WETextSecondary
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        // ------------------------------------------
        // Login Button
        // ------------------------------------------

        Button(
            onClick = viewModel::login,
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {

            if (state.isLoading) {

                CircularProgressIndicator(
                    modifier = Modifier.height(20.dp),
                    color = WEPrimary,
                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    text = "Login"
                )
            }
        }
    }
}