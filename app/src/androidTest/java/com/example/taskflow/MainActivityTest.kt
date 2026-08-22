package com.example.taskflow

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.taskflow.domain.model.User
import com.example.taskflow.ui.theme.TaskFlowTheme
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingIndicatorIsDisplayed() {

        // Arrange
        val fakeRepository = FakeUserRepository()

        composeTestRule.setContent {
            TaskFlowTheme {
                TaskFlowScreen(
                    repository = fakeRepository
                )
            }
        }

        // Assert: Loading is displayed
        composeTestRule
            .onNodeWithTag("loading_indicator")
            .assertIsDisplayed()

        // Assert: Success is not displayed yet
        composeTestRule
            .onNodeWithText("Tasks: 3")
            .assertDoesNotExist()
    }

    @Test
    fun tasksAreDisplayed() {

        // Arrange
        val fakeRepository = FakeUserRepository()

        composeTestRule.setContent {
            TaskFlowTheme {
                TaskFlowScreen(
                    repository = fakeRepository
                )
            }
        }

        val users = listOf(
            User(1, "Zaid"),
            User(2, "Ahmed"),
            User(3, "Mohamed")
        )

        // Act
        composeTestRule.runOnIdle {
            fakeRepository.usersFlow.tryEmit(users)
        }

        // Assert
        composeTestRule
            .onNodeWithText("Tasks: 3")
            .assertIsDisplayed()
    }

    @Test
    fun loadingChangesToSuccess() {

        // Arrange
        val fakeRepository = FakeUserRepository()

        composeTestRule.setContent {
            TaskFlowTheme {
                TaskFlowScreen(
                    repository = fakeRepository
                )
            }
        }

        // Verify Loading
        composeTestRule
            .onNodeWithTag("loading_indicator")
            .assertIsDisplayed()

        // Act
        val users = listOf(
            User(1, "Zaid"),
            User(2, "Ahmed"),
            User(3, "Mohamed")
        )

        composeTestRule.runOnIdle {
            fakeRepository.usersFlow.tryEmit(users)
        }

        // Verify Success
        composeTestRule
            .onNodeWithText("Tasks: 3")
            .assertIsDisplayed()
    }
}