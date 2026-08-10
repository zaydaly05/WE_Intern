package com.example.formalpullrequest.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formalpullrequest.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun onPhoneNumberChanged(value: String) {
        _uiState.update {
            it.copy(
                phoneNumber = value,
                errorMessage = null
            )
        }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update {
            it.copy(
                password = value,
                errorMessage = null
            )
        }
    }

    fun login() {

        val state = _uiState.value

        if (state.phoneNumber.isBlank()) {
            _uiState.update {
                it.copy(
                    errorMessage = "Please enter your phone number."
                )
            }
            return
        }

        if (state.password.isBlank()) {
            _uiState.update {
                it.copy(
                    errorMessage = "Please enter your password."
                )
            }
            return
        }

        val password = state.password.toIntOrNull()

        if (password == null) {
            _uiState.update {
                it.copy(
                    errorMessage = "Password must be a number."
                )
            }
            return
        }

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {

                val customerId = loginRepository.login(
                    phoneNumber = state.phoneNumber,
                    password = password
                )

                if (customerId != null) {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoggedIn = true,
                            customerId = customerId
                        )
                    }

                } else {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Invalid phone number or password."
                        )
                    }
                }

            } catch (exception: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Unable to connect to the server."
                    )
                }
            }
        }
    }

    fun clearLoginState() {
        _uiState.update {
            it.copy(
                isLoggedIn = false
            )
        }
    }
}