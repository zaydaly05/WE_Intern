package com.example.composetesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composetesting.ui.screens.LoginScreen
import com.example.composetesting.ui.theme.ComposeTestingTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreen_displaysEmailAndPasswordFields() {

        composeTestRule.setContent {

            ComposeTestingTheme {

                LoginScreen(
                    onLoginSuccess = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Email")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Password")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Login")
            .assertIsDisplayed()
    }

    @Test
    fun enteringEmailAndPassword_works() {

        composeTestRule.setContent {

            ComposeTestingTheme {

                LoginScreen(
                    onLoginSuccess = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("test@example.com")

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("password123")
    }

    @Test
    fun clickingLogin_displaysLoadingIndicator() {

        composeTestRule.setContent {

            ComposeTestingTheme {

                LoginScreen(
                    onLoginSuccess = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Login")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Loading")
            .assertIsDisplayed()
    }
}