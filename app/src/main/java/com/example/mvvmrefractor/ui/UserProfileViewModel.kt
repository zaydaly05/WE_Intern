package com.example.mvvmrefractor.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserProfileUiState())

    val uiState: StateFlow<UserProfileUiState> =
        _uiState.asStateFlow()

    fun onNameChanged(newName: String) {
        _uiState.value =
            _uiState.value.copy(name = newName)
    }

    fun onButtonClicked() {
        _uiState.value =
            _uiState.value.copy(
                clickCount = _uiState.value.clickCount + 1
            )
    }
}