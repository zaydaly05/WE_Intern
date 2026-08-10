package com.example.formalpullrequest.presentation.navigation

sealed class Screen(
    val route: String
) {

    data object Home : Screen("home")

    data object Services : Screen("services")

    data object Usage : Screen("usage")

    data object Support : Screen("support")

    data object Profile : Screen("profile")

    data object Activity : Screen("activity")
}