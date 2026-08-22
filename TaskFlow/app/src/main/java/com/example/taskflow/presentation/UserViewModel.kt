package com.example.taskflow.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UserUiState>(UserUiState.Loading)

    val uiState: StateFlow<UserUiState> =
        _uiState.asStateFlow()

    fun loadUsers() {
        viewModelScope.launch {
            getUsersUseCase()
                .onStart {
                    _uiState.value = UserUiState.Loading
                }
                .catch { exception ->
                    _uiState.value =
                        UserUiState.Error(
                            exception.message ?: "Unknown error"
                        )
                }
                .collect { users ->
                    _uiState.value =
                        UserUiState.Success(users)
                }
        }
    }
}