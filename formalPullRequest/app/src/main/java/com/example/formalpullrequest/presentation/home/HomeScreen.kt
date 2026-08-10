package com.example.formalpullrequest.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formalpullrequest.ui.components.WECard
import com.example.formalpullrequest.ui.components.WESectionTitle
import com.example.formalpullrequest.ui.components.WEStatusChip
import com.example.formalpullrequest.ui.theme.WEBackground
import com.example.formalpullrequest.ui.theme.WEPrimary
import com.example.formalpullrequest.ui.theme.WETextPrimary
import com.example.formalpullrequest.ui.theme.WETextSecondary

@Composable
fun HomeScreen(
    customerId: String? = null,
    onLogout: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(customerId) {
        customerId?.let {
            viewModel.loadCustomer(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WEBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        // ==================================================
        // HEADER
        // ==================================================

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Good morning",
                    color = WETextSecondary
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = state.customer?.name ?: "WE Customer",
                    color = WETextPrimary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = WEPrimary
                    )
                }

                IconButton(
                    onClick = onLogout
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Logout",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // ==================================================
        // INITIAL LOADING
        // ==================================================

        if (state.isLoading && state.customer == null) {

            CircularProgressIndicator(
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                ),
                color = WEPrimary
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Loading your account...",
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                ),
                color = WETextSecondary
            )

        } else {

            // ==================================================
            // ERROR MESSAGE
            // ==================================================

            state.errorMessage?.let { error ->

                WECard(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column {

                        Text(
                            text = "Unable to update account",
                            color = WETextPrimary
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = error,
                            color = WETextSecondary
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }

            // ==================================================
            // CUSTOMER CONTENT
            // ==================================================

            state.customer?.let { customer ->

                // ==================================================
                // CURRENT PLAN
                // ==================================================

                WECard(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {

                        Column {

                            Text(
                                text = "Current plan",
                                color = WETextSecondary
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = customer.planName,
                                color = WETextPrimary
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = "${customer.planPrice} EGP / month",
                                color = WETextSecondary
                            )
                        }

                        WEStatusChip(
                            text = "Active"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                // ==================================================
                // DATA USAGE
                // ==================================================

                WESectionTitle(
                    title = "Data usage"
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                WECard(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "${customer.dataUsedGb} GB used",
                        color = WETextPrimary
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    LinearProgressIndicator(
                        progress = {
                            customer.dataUsagePercentage
                        },
                        modifier = Modifier.fillMaxWidth(),
                        color = WEPrimary
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "${customer.dataLimitGb} GB total",
                        color = WETextSecondary
                    )
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                // ==================================================
                // ACCOUNT
                // ==================================================

                WESectionTitle(
                    title = "Account"
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                WECard(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Account number",
                        color = WETextSecondary
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = customer.accountNumber,
                        color = WETextPrimary
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = "Phone number",
                        color = WETextSecondary
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = customer.phoneNumber,
                        color = WETextPrimary
                    )
                }
            }

            // ==================================================
            // REFRESH
            //
            // IMPORTANT:
            // This is outside state.customer?.let
            // so it is visible even when customer == null.
            // ==================================================

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (state.isRefreshing) {

                    CircularProgressIndicator(
                        modifier = Modifier.height(20.dp),
                        color = WEPrimary,
                        strokeWidth = 2.dp
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                }

                IconButton(
                    onClick = {
                        state.customer?.id?.toString()?.let { customerId ->
                            viewModel.refresh(customerId)
                        }
                    },
                    enabled = !state.isRefreshing
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh customer data",
                        tint = WEPrimary
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}