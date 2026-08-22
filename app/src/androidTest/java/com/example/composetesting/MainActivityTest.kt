package com.example.composetesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loginButton_isDisplayed() {

        composeTestRule
            .onNodeWithText("Login")
            .assertIsDisplayed()
    }

    @Test
    fun clickingLogin_showsLoadingSpinner() {

        composeTestRule
            .onNodeWithText("Login")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Loading")
            .assertIsDisplayed()
    }

    @Test
    fun clickingLogin_navigatesToHomeScreen() {

        composeTestRule
            .onNodeWithText("Login")
            .performClick()

        composeTestRule
            .waitUntil(
                timeoutMillis = 3000
            ) {
                composeTestRule
                    .onAllNodesWithText("Home Screen")
                    .fetchSemanticsNodes()
                    .isNotEmpty()
            }

        composeTestRule
            .onNodeWithText("Home Screen")
            .assertIsDisplayed()
    }
}