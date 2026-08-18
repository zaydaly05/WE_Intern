package com.capstone.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.feature.profile.domain.GetProfileUseCase
import com.capstone.feature.profile.domain.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProfileUiState {
    object Loading : ProfileUiState
    data class Success(val profile: Profile) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            try {
                android.util.Log.d("ProfileViewModel", "Loading profile...")
                val profile = getProfileUseCase()
                android.util.Log.d("ProfileViewModel", "Profile loaded: $profile")
                _uiState.value = ProfileUiState.Success(profile)
            } catch (e: Exception) {
                android.util.Log.e("ProfileViewModel", "Error loading profile", e)
                _uiState.value = ProfileUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
