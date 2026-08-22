package com.example.taskflow


import app.cash.turbine.test
import com.example.taskflow.domain.model.User
import com.example.taskflow.domain.repository.UserRepository
import com.example.taskflow.domain.usecase.GetUsersUseCase
import com.example.taskflow.presentation.UserUiState
import com.example.taskflow.presentation.UserViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: UserRepository = mockk()

    private lateinit var viewModel: UserViewModel

    @Test
    fun `loadUsers emits Success when repository returns users`() = runTest {

        // Arrange
        val users = listOf(
            User(1, "Zaid"),
            User(2, "Ahmed")
        )

        every {
            repository.getUsers()
        } returns flowOf(users)

        val useCase = GetUsersUseCase(repository)

        viewModel = UserViewModel(useCase)

        // Act
        viewModel.loadUsers()

        advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value

        assertEquals(
            UserUiState.Success(users),
            state
        )
    }
    @Test
    fun `loadUsers emits Error when repository throws exception`() = runTest {

        // Arrange
        every {
            repository.getUsers()
        } returns kotlinx.coroutines.flow.flow {
            throw RuntimeException("Network error")
        }

        val useCase = GetUsersUseCase(repository)

        viewModel = UserViewModel(useCase)

        // Act
        viewModel.loadUsers()

        advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value

        assertEquals(
            UserUiState.Error("Network error"),
            state
        )
    }
    @Test
    fun `loadUsers emits Loading then Success`() = runTest {

        val users = listOf(
            User(1, "Zaid")
        )

        every {
            repository.getUsers()
        } returns flowOf(users)

        val useCase = GetUsersUseCase(repository)

        viewModel = UserViewModel(useCase)

        viewModel.uiState.test {

            // Initial state
            assertEquals(
                UserUiState.Loading,
                awaitItem()
            )

            viewModel.loadUsers()

            advanceUntilIdle()

            val successState = awaitItem()

            assertEquals(
                UserUiState.Success(users),
                successState
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
}