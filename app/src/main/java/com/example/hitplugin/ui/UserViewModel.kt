package com.example.hitplugin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hitplugin.data.UserRepository
import com.example.hitplugin.database.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UserUiState {

    data object Loading : UserUiState

    data class Success(
        val users: List<UserEntity>
    ) : UserUiState

    data class Error(
        val message: String
    ) : UserUiState
}

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UserUiState>(UserUiState.Loading)

    val uiState: StateFlow<UserUiState> =
        _uiState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {

        viewModelScope.launch {

            _uiState.value = UserUiState.Loading

            try {

                val users = repository.getUsers()

                _uiState.value = UserUiState.Success(
                    users = users
                )

            } catch (e: Exception) {

                _uiState.value = UserUiState.Error(
                    message = e.message ?: "Something went wrong"
                )
            }
        }
    }
}