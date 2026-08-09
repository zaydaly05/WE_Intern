package com.example.nativenavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.nativenavigation.ui.details.DetailsScreen
import com.example.nativenavigation.ui.feed.FeedScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Feed
    ) {

        composable<Feed> {

            FeedScreen(
                onPostClick = { id ->
                    navController.navigate(
                        Details(id)
                    )
                }
            )
        }

        composable<Details> { backStackEntry ->

            val details = backStackEntry.toRoute<Details>()

            DetailsScreen(
                postId = details.postId
            )
        }
    }
}