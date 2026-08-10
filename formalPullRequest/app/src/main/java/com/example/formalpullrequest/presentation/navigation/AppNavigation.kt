package com.example.formalpullrequest.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.formalpullrequest.presentation.activity.ActivityScreen
import com.example.formalpullrequest.presentation.home.HomeScreen
import com.example.formalpullrequest.presentation.login.LoginScreen
import com.example.formalpullrequest.presentation.profile.ProfileScreen
import com.example.formalpullrequest.presentation.services.InternetScreen
import com.example.formalpullrequest.presentation.services.MobileServicesScreen
import com.example.formalpullrequest.presentation.services.RouterScreen
import com.example.formalpullrequest.presentation.services.ServicesScreen
import com.example.formalpullrequest.presentation.services.SimManagementScreen
import com.example.formalpullrequest.presentation.usage.UsageScreen

private data class NavigationItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    var customerIdState by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    val logout = {
        customerIdState = null
        navController.navigate("login") {
            popUpTo(0) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    val items = listOf(
        NavigationItem(
            route = "home",
            label = "Home",
            icon = Icons.Default.Home
        ),
        NavigationItem(
            route = "services",
            label = "Services",
            icon = Icons.Default.Wifi
        ),
        NavigationItem(
            route = "usage",
            label = "Usage",
            icon = Icons.Default.Timeline
        ),
        NavigationItem(
            route = "activity",
            label = "Activity",
            icon = Icons.Default.Timeline
        ),
        NavigationItem(
            route = "profile",
            label = "Profile",
            icon = Icons.Default.Person
        )
    )

    Scaffold(
        bottomBar = {

            val backStackEntry by navController
                .currentBackStackEntryAsState()

            val currentRoute =
                backStackEntry?.destination?.route

            if (currentRoute != "login") {

                NavigationBar {

                    items.forEach { item ->

                        val isSelected =
                            currentRoute?.startsWith(item.route) == true

                        NavigationBarItem(
                            selected = isSelected,

                            onClick = {

                                if (item.route == "home") {

                                    if (customerIdState != null) {

                                        navController.navigate(
                                            "home?customerId=$customerIdState"
                                        ) {
                                            popUpTo("home") {
                                                saveState = true
                                            }

                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }

                                } else {

                                    navController.navigate(item.route) {

                                        popUpTo("home") {
                                            saveState = true
                                        }

                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },

                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },

                            label = {
                                Text(item.label)
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,

            startDestination = "login",

            modifier = Modifier.padding(innerPadding)
        ) {

            // ---------------------------------------------------------
            // LOGIN
            // ---------------------------------------------------------

            composable("login") {

                LoginScreen(
                    onLoginSuccess = { customerId ->

                        customerIdState = customerId

                        navController.navigate(
                            "home?customerId=$customerId"
                        ) {

                            popUpTo("login") {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            }

            // ---------------------------------------------------------
            // HOME
            // ---------------------------------------------------------

            composable(
                route = "home?customerId={customerId}",

                arguments = listOf(
                    navArgument("customerId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val customerId =
                    backStackEntry.arguments
                        ?.getString("customerId")

                HomeScreen(
                    customerId = customerId,
                    onLogout = logout
                )
            }

            // ---------------------------------------------------------
            // SERVICES
            // ---------------------------------------------------------

            composable("services") {

                ServicesScreen(
                    onServiceClick = { route ->

                        navController.navigate(route)
                    }
                )
            }

            // ---------------------------------------------------------
            // MOBILE SERVICES
            // ---------------------------------------------------------

            composable("mobile_services") {

                MobileServicesScreen()
            }

            // ---------------------------------------------------------
            // INTERNET
            // ---------------------------------------------------------

            composable("internet") {

                InternetScreen()
            }

            // ---------------------------------------------------------
            // ROUTER
            // ---------------------------------------------------------

            composable("router") {

                RouterScreen()
            }

            // ---------------------------------------------------------
            // SIM MANAGEMENT
            // ---------------------------------------------------------

            composable("sim_management") {

                SimManagementScreen()
            }

            // ---------------------------------------------------------
            // USAGE
            // ---------------------------------------------------------

            composable("usage") {

                UsageScreen()
            }

            // ---------------------------------------------------------
            // ACTIVITY
            // ---------------------------------------------------------

            composable("activity") {

                ActivityScreen()
            }

            // ---------------------------------------------------------
            // PROFILE
            // ---------------------------------------------------------

            composable("profile") {

                ProfileScreen(
                    onLogout = logout
                )
            }
        }
    }
}